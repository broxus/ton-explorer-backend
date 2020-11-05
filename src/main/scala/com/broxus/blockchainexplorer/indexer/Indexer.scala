package com.broxus.blockchainexplorer.indexer

import java.math.BigInteger

import com.broxus.blockchainexplorer.ShardHelper
import com.broxus.blockchainexplorer.config.IndexerConfig
import com.broxus.blockchainexplorer.repository.{ DBAccountRepository, DBBlockRepository }
import com.broxus.blockchainexplorer.repository.models._
import com.broxus.blockchainexplorer.ton.{ TonApiErrorException, TonService }
import com.broxus.ton.TonApi
import com.broxus.ton.TonApi._
import zio.logging.Logger
import zio.{ Task, ZIO }

import scala.math.BigDecimal.RoundingMode

class Indexer(
    config: IndexerConfig,
    logger: Logger[String],
    tonService: TonService.Service,
    dbBlockRepository: DBBlockRepository.Service,
    dbAccountRepository: DBAccountRepository.Service
) {

  private val STEP_SIZE             = config.stepSize
  private val MAX_THREADS_PER_SHARD = config.threadsPerShard

  implicit def dbBlockToApiBlockId(dbBlock: DBBlock): TonApi.TonBlockIdExt =
    new TonApi.TonBlockIdExt(dbBlock.workchain, dbBlock.shard, dbBlock.seqno, dbBlock.roothash, dbBlock.filehash)

  implicit def extIdToId(extId: TonApi.TonBlockIdExt): TonApi.TonBlockId =
    new TonApi.TonBlockId(extId.workchain, extId.shard, extId.seqno)

  def getTopBlocks: Task[Seq[TonBlockId]] =
    for {
      dbItems   <- dbBlockRepository.getTopBlocks()
      topBlocks <- ZIO.foreachPar(dbItems.groupBy(_._1).values.toSeq) { g =>
                     tonService
                       .computeLastBlockIds(g.map(tuple => new TonApi.TonBlockId(tuple._1, tuple._2, tuple._3)))
                       .map(_.ids.toSeq)
                   }
      _         <- logger.info(
                     "Top blocks: " ++ topBlocks
                       .flatten
                       .map { bId =>
                         s"(${bId.workchain}, ${bId.shard.toHexString}, ${bId.seqno})"
                       }
                       .mkString(",")
                   )
    } yield topBlocks.flatten

  def run(): ZIO[Any, Throwable, Unit] =
    for {
      _          <- logger.debug(s"Start indexer. Loading state from db...")
      topBlocks  <- getTopBlocks
      _          <- processZeroState().when(topBlocks.isEmpty)
      startBlocks = if (topBlocks.isEmpty) config.zeroState.map(z => (z.workchain, z.shard, z.seqno))
                    else topBlocks.map(b => (b.workchain, b.shard, b.seqno))
      _          <- runEachShardInSelfThreads(startBlocks)
    } yield ()

  def processZeroState(): ZIO[Any, Throwable, Unit] =
    for {
      _               <- logger.debug(s"Process zero-state")
      masterchainInfo <- tonService.liteServerGetMasterchainInfo()
      zeroState       <- tonService.liteServerGetBlockState(
                           new TonApi.TonBlockIdExt(
                             masterchainInfo.init.workchain,
                             config.zeroState.filter(_.workchain == masterchainInfo.init.workchain).head.shard,
                             0,
                             masterchainInfo.init.rootHash,
                             masterchainInfo.init.fileHash
                           )
                         )

      _               <- dbAccountRepository.upsert(
                           zeroState
                             .accounts
                             .map { zeroStateAccount =>
                               val (workchain, address) = getAccount(zeroStateAccount.addr)
                               DBAccount(
                                 workchain = workchain,
                                 address = address,
                                 balance = bytesToBigDecimalSafe(zeroStateAccount.balance.grams).getOrElse(BigDecimal(0)),
                                 created = 0,
                                 updated = 0
                               )
                             }
                             .toList
                         )
    } yield ()

  def runEachShardInSelfThreads(
      topBlocks: Seq[(Int, Long, Int)],
      threads: Map[(Int, Long), Int] = Map.empty
  ): ZIO[Any, Throwable, Unit] =
    for {
      _          <- logger.info(
                      "Checkpoint: \n" ++ topBlocks
                        .sortBy(-_._3)
                        .map(e => s"(${e._1}, ${e._2.toHexString}, ${e._3})")
                        .mkString("\n")
                    )
      t0         <- Task(System.currentTimeMillis())
      results    <- ZIO.foreachPar(topBlocks) {
                      case (w, s, n) =>
                        multipleThreads(w, s, n, threads.getOrElse((w, s), MAX_THREADS_PER_SHARD))
                    }
      t1         <- Task(System.currentTimeMillis())
      _          <- ZIO.foreachPar_(results) { r =>
                      saveResults(r._1)
                    }
      t2         <- Task(System.currentTimeMillis())
      _          <- logger.info(
                      s"${results.flatMap(_._1).size} blocks." +
                        s"\nResults in ${t1 - t0}. Index + saving in ${t2 - t1}\n\n"
                    )
      nextThreads =
        results
          .flatMap(r =>
            r._2
              .map(e => (e._1, e._2))
              .map { key =>
                val resultsCount           = r._1.size
                val goodThreads            = (BigDecimal(s"$resultsCount") / STEP_SIZE).setScale(0, RoundingMode.UP).toInt
                val currentCycleMaxResults = threads.getOrElse(key, MAX_THREADS_PER_SHARD) * STEP_SIZE
                val needIncrement          = goodThreads < MAX_THREADS_PER_SHARD && resultsCount == currentCycleMaxResults
                key -> (goodThreads + (if (needIncrement || goodThreads == 0) 1 else 0))
              }
          )
          .toMap
      _          <- Task {
                      if (t2 - t0 < 1000 && (results.isEmpty || results.map(_._1.size).sum == 0))
                        Thread.sleep(1000 - (t2 - t0))
                    }
      _          <- runEachShardInSelfThreads(results.flatMap(_._2).distinct, nextThreads)
    } yield ()

  def saveResults(blocks: Seq[TonApi.LiteServerBlock]): ZIO[Any, Throwable, Unit] =
    for {
      (bb, tt, mm, stt) <- ZIO
                             .foreachPar(blocks) { b =>
                               indexBlock(b)
                             }
                             .map { index =>
                               (
                                 index.map(_._1).toList,
                                 index.flatMap(_._2).toList,
                                 index.flatMap(_._3).toList,
                                 index.flatMap(_._4).toList
                               )
                             }
      _                 <- dbBlockRepository.upsertBatch(bb, tt, mm, stt).when(bb.nonEmpty)
    } yield ()

  def multipleThreads(
      workchain: Int,
      shard: Long,
      seqno: Int,
      threads: Int = MAX_THREADS_PER_SHARD
  ): ZIO[Any, Throwable, (Seq[TonApi.LiteServerBlock], Seq[(Int, Long, Int)])] = {
    val threadsNumbers     = 0 until threads
    val threadsStartBlocks = threadsNumbers.map(t => (workchain, shard, seqno + (t * STEP_SIZE)))

    for {
      _           <- logger
                       .debug(s"For ($workchain, ${shard.toHexString}) threads count = $threads in this cycle.")
                       .when(threads != MAX_THREADS_PER_SHARD)
      fibers      <- ZIO.foreach(threadsNumbers.toList)(t => step(threadsStartBlocks(t), STEP_SIZE).map(r => t -> r).fork)
      joined      <- ZIO.foreach(fibers)(_.join).map(_.toMap)
      validUntil   = (1 until threads).filterNot { t =>
                       joined(t - 1)._2.length == 1 && joined(t - 1)._2.contains(threadsStartBlocks(t))
                     }.minOption.getOrElse(threads)
      _           <-
        logger
          .warn(
            s"For ($workchain, ${shard.toHexString}) only $validUntil of $threads threads has valid results. Invalid results dropped."
          )
          .when(validUntil != threads)
      validResults = (0 until validUntil).foldLeft(Seq.empty[TonApi.LiteServerBlock]) {
                       case (elements, t) => elements ++ joined(t)._1
                     }
    } yield (validResults, joined(validUntil - 1)._2)
  }

  def step(
      blockId: (Int, Long, Int),
      tick: Int
  ): ZIO[Any, Throwable, (Seq[TonApi.LiteServerBlock], Seq[(Int, Long, Int)])] =
    tonService
      .liteServerLookupBlock(new TonApi.TonBlockId(blockId._1, blockId._2, blockId._3))
      .foldM(
        e =>
          for {
            _ <- logger
                   .throwable(s"Error lookup block (${blockId._1}, ${blockId._2.toHexString}, ${blockId._3}): ", e)
                   .unless(
                     e.isInstanceOf[TonApiErrorException] && e
                       .asInstanceOf[TonApiErrorException]
                       .error
                       .message == "LITE_SERVER_NOTREADY: ltdb: block not found"
                   )
          } yield (Seq.empty[TonApi.LiteServerBlock], Seq(blockId)),
        block =>
          for {
            next            <- Task {
                                 if (block.info.beforeSplit) {
                                   val (left, right) = ShardHelper.shard_childs(block.id.shard)
                                   Seq(
                                     (block.id.workchain, left, block.id.seqno + 1),
                                     (block.id.workchain, right, block.id.seqno + 1)
                                   )
                                 } else if (block.id.shard != blockId._2 && ShardHelper.is_right_child(blockId._2))
                                   Nil
                                 else Seq((block.id.workchain, block.id.shard, block.id.seqno + 1))
                               }
            (blocks, nexts) <- if (tick > 1)
                                 for {
                                   childs <- ZIO.foreachPar(next) { e =>
                                               step(e, tick - 1)
                                             }
                                   result  = childs.foldLeft((Seq(block), Seq.empty[(Int, Long, Int)]))((a, b) =>
                                               (a._1 ++ b._1, a._2 ++ b._2)
                                             )
                                 } yield result
                               else
                                 ZIO.succeed((Seq(block), next))
          } yield (blocks, nexts)
      )

  def indexBlock(
      block: TonApi.LiteServerBlock
  ): ZIO[Any, Throwable, (DBBlock, Seq[DBTransaction], Seq[DBMessage], Seq[DBStakeTransaction])] =
    for {
      _             <- logger.debug(
                         s"Indexing block (${block.id.workchain}, ${block.id.shard}, ${block.id.seqno}). " +
                           s"Block have ${block.transactions.length} transactions"
                       )
      dbBlock        = createDbBlock(block)
      (tt, mm, stt) <- ZIO
                         .foreachPar(block.transactions.toSeq) { transaction =>
                           indexTransaction(block.id, transaction)
                         }
                         .map(items => (items.map(_._1), items.flatMap(_._2), items.flatMap(_._3)))
    } yield (dbBlock, tt, mm, stt)

  def indexTransaction(
      blockId: TonApi.TonBlockIdExt,
      tx: TonApi.LiteServerTransaction
  ): Task[(DBTransaction, Seq[DBMessage], Option[DBStakeTransaction])] =
    Task {
      val dbTransaction         = createDbTransaction(blockId, tx)
      val dbInMessageOpt        = Option(tx.inMsg).map(createDbMsg(tx, _, out = false, 0))
      val dbOutMessages         = tx.outMsgs.toSeq.zipWithIndex.map {
        case (msg, index) => createDbMsg(tx, msg, out = true, index)
      }
      val dbStakeTransactionOpt = createDbStakeTransaction(tx)
      (dbTransaction, dbInMessageOpt.toSeq ++ dbOutMessages, dbStakeTransactionOpt)
    }

  private def bytesToBigDecimalSafe(bytes: Array[Byte]) = Option(bytes).map(b => BigDecimal(new BigInteger(b)))

  private def msgValueSafe(msg: LiteServerMessage) =
    Option(msg)
      .flatMap(_.info match {
        case info: LiteServerMessageInfoInt => Some(info)
        case _                              => None
      })
      .flatMap(info => bytesToBigDecimalSafe(info.value))

  private def msgFwdFeeSafe(msg: LiteServerMessage) =
    Option(msg)
      .flatMap(_.info match {
        case info: LiteServerMessageInfoInt => Some(info)
        case _                              => None
      })
      .flatMap(info => bytesToBigDecimalSafe(info.fwdFee))

  private def msgIhrFeeSafe(msg: LiteServerMessage) =
    Option(msg)
      .flatMap(_.info match {
        case info: LiteServerMessageInfoInt => Some(info)
        case _                              => None
      })
      .flatMap(info => bytesToBigDecimalSafe(info.ihrFee))

  private def msgImportFeeSafe(msg: LiteServerMessage) =
    Option(msg)
      .flatMap(_.info match {
        case info: LiteServerMessageInfoExtIn => Some(info)
        case _                                => None
      })
      .flatMap(info => bytesToBigDecimalSafe(info.importFee))

  private def createDbMsg(tx: TonApi.LiteServerTransaction, msg: LiteServerMessage, out: Boolean, n: Int): DBMessage = {
    val (messageType, src, dst, bounce, bounced, createdLt, createdAt, importFee, ihrFee, fwdFee, value) =
      extractMessageInfo(msg.info)

    DBMessage(
      bodyHash = msg.hash,
      out = out,
      n = n,
      transactionWorkchain = tx.workchain,
      transactionAccountId = tx.account,
      transactionHash = tx.hash,
      transactionLt = tx.lt,
      messageType = messageType,
      transactionTime = tx.now,
      value = value,
      ihrFee = ihrFee,
      fwdFee = fwdFee,
      importFee = importFee,
      srcWorkchain = src.map(_._1),
      srcAddress = src.map(_._2),
      dstWorkchain = dst.map(_._1),
      dstAddress = dst.map(_._2),
      bounce,
      bounced,
      createdLt,
      createdAt
    )
  }

  private def getTransactionFees(tx: TonApi.LiteServerTransaction): (
      BigDecimal,         // totalFees
      Option[BigDecimal], // storageFeesCollected
      Option[BigDecimal], // storageFeesDue
      Option[BigDecimal], // dueFeesCollected
      Option[BigDecimal], // gasFees
      Option[BigDecimal], // totalFwdFees
      Option[BigDecimal], // totalActionFees
      Option[BigDecimal], // reqFwdFees
      Option[BigDecimal], // msgFees
      Option[BigDecimal]  // fwdFees
  ) = {

    val totalFees = BigDecimal(new BigInteger(tx.totalFees))

    val (_, _, _, _, _, storagePh, creditPh, computePh, actionPh, bouncePh) =
      extractTransactionDescription(tx.desc)
    val storageFeesCollected                                                =
      storagePh.flatMap(e => Option(e.storageFeesCollected)).map(e => BigDecimal(new BigInteger(e)))
    val storageFeesDue                                                      =
      storagePh
        .filter(_.hasStorageFeesDue)
        .flatMap(e => Option(e.storageFeesDue))
        .map(e => BigDecimal(new BigInteger(e)))
    val dueFeesCollected                                                    =
      creditPh
        .filter(_.hasDueFeesCollected)
        .flatMap(e => Option(e.dueFeesCollected))
        .map(e => BigDecimal(new BigInteger(e)))
    val gasFees                                                             =
      computePh
        .filter(_.isInstanceOf[LiteServerTransactionComputePhaseVm])
        .map(_.asInstanceOf[LiteServerTransactionComputePhaseVm])
        .flatMap(e => Option(e.gasFees))
        .map(e => BigDecimal(new BigInteger(e)))
    val totalFwdFees                                                        =
      actionPh
        .filter(_.hasTotalFwdFees)
        .flatMap(e => Option(e.totalFwdFees))
        .map(e => BigDecimal(new BigInteger(e)))
    val totalActionFees                                                     =
      actionPh
        .filter(_.hasTotalActionFees)
        .flatMap(e => Option(e.totalActionFees))
        .map(e => BigDecimal(new BigInteger(e)))
    val (reqFwdFees, msgFees, fwdFees)                                      = bouncePh match {
      case Some(e: LiteServerTransactionBouncePhaseNoFunds) =>
        (Option(e.reqFwdFees).map(e => BigDecimal(new BigInteger(e))), None, None)
      case Some(e: LiteServerTransactionBouncePhaseOk)      =>
        (None, bytesToBigDecimalSafe(e.msgFees), bytesToBigDecimalSafe(e.fwdFees))
      case _                                                => (None, None, None)
    }

    (
      totalFees,
      storageFeesCollected,
      storageFeesDue,
      dueFeesCollected,
      gasFees,
      totalFwdFees,
      totalActionFees,
      reqFwdFees,
      msgFees,
      fwdFees
    )

  }

  private def extractTransactionDescription(
      desc: TonApi.LiteServerTransactionDescr
  ): (
      TransactionType,                                  // type
      Option[Boolean],                                  // aborted
      Option[Boolean],                                  // destroyed
      Option[Boolean],                                  // creditFirst
      Option[Boolean],                                  // isTock
      Option[TonApi.LiteServerTransactionStoragePhase], // storagePh
      Option[TonApi.LiteServerTransactionCreditPhase],  // creditPh
      Option[TonApi.LiteServerTransactionComputePhase], // computePh
      Option[TonApi.LiteServerTransactionActionPhase],  // action
      Option[TonApi.LiteServerTransactionBouncePhase]   // bounce
  ) =
    desc match {
      case a: LiteServerTransactionDescrMergeInstall =>
        (
          TransactionType.MergeInstall,
          Some(a.aborted),
          Some(a.destroyed),
          None,
          None,
          Some(a.storagePh),
          Some(a.creditPh),
          Some(a.computePh),
          Some(a.action),
          None
        )
      case a: LiteServerTransactionDescrMergePrepare =>
        (TransactionType.MergePrepare, Some(a.aborted), None, None, None, Some(a.storagePh), None, None, None, None)
      case a: LiteServerTransactionDescrOrdinary     =>
        (
          TransactionType.Ordinary,
          Option(a.aborted),
          Option(a.destroyed),
          Option(a.creditFirst),
          None,
          Option(a.storagePh),
          Option(a.creditPh),
          Option(a.computePh),
          Option(a.action),
          Option(a.bounce)
        )
      case a: LiteServerTransactionDescrSplitPrepare =>
        (
          TransactionType.SplitPrepare,
          Option(a.aborted),
          Option(a.destroyed),
          None,
          None,
          Option(a.storagePh),
          None,
          Option(a.computePh),
          Option(a.action),
          None
        )
      case a: LiteServerTransactionDescrTickTock     =>
        (
          TransactionType.TickTok,
          Option(a.aborted),
          Option(a.destroyed),
          None,
          Option(a.isTock),
          Option(a.storagePh),
          None,
          Option(a.computePh),
          Option(a.action),
          None
        )
      case a: LiteServerTransactionDescrStorage      =>
        (TransactionType.Storage, None, None, None, None, Option(a.storagePh), None, None, None, None)
      case a: LiteServerTransactionDescrSplitInstall =>
        (TransactionType.SplitInstall, None, None, None, None, None, None, None, None, None)
      case _                                         =>
        (TransactionType.Unknown, None, None, None, None, None, None, None, None, None)
    }

  private def createDbTransaction(blockId: TonApi.TonBlockIdExt, tx: TonApi.LiteServerTransaction): DBTransaction = {

    val (
      totalFees,
      storageFeesCollected,
      storageFeesDue,
      dueFeesCollected,
      gasFees,
      totalFwdFees,
      totalActionFees,
      reqFwdFees,
      msgFees,
      fwdFees
    ) = getTransactionFees(tx)

    val (transactionType, aborted, destroyed, creditFirst, isTock, _, _, _, _, _) =
      extractTransactionDescription(tx.desc)

    val balanceChange = if (transactionType == TransactionType.Ordinary) {
      val income            = msgValueSafe(tx.inMsg).getOrElse(BigDecimal(0))
      val outcome           = tx.outMsgs.flatMap(msgValueSafe).sum
      val outMsgsFwdFees    = tx.outMsgs.flatMap(msgFwdFeeSafe).sum
      val outMsgsIhrFees    = tx.outMsgs.flatMap(msgIhrFeeSafe).sum
      val outMsgsImportFees = tx.outMsgs.flatMap(msgImportFeeSafe).sum
      income - outcome - totalFees - outMsgsFwdFees - outMsgsIhrFees - outMsgsImportFees
    } else BigDecimal(0)

    DBTransaction(
      workchain = tx.workchain,
      accountId = tx.account,
      hash = tx.hash,
      lt = tx.lt,
      blockShard = blockId.shard,
      blockSeqno = blockId.seqno,
      transactionType,
      balanceChange = balanceChange,
      time = tx.now,
      totalFees = totalFees,
      storageFeesCollected = storageFeesCollected,
      storageFeesDue = storageFeesDue,
      dueFeesCollected = dueFeesCollected,
      gasFees = gasFees,
      totalFwdFees = totalFwdFees,
      totalActionFees = totalActionFees,
      reqFwdFees = reqFwdFees,
      msgFees = msgFees,
      fwdFees = fwdFees,
      aborted,
      destroyed,
      creditFirst,
      isTock
    )
  }

  private def getAccount(data: LiteServerMessageAddressInt): (Int, Array[Byte]) =
    data match {
      case a: TonApi.LiteServerMessageAddressIntStd        => (a.workchain, a.address)
      case b: TonApi.LiteServerMessageAddressIntStdAnycast => (b.workchain, b.address)
      case c: TonApi.LiteServerMessageAddressIntVar        => (c.workchain, c.address)
      case d: TonApi.LiteServerMessageAddressIntVarAnycast => (d.workchain, d.address)
    }

  private def getAddress(data: TonApi.LiteServerMessageAddressExt): Option[Array[Byte]] =
    data match {
      case a: TonApi.LiteServerMessageAddressExtSome => Option(a.externalAddress)
      case _                                         => None
    }

  private def extractMessageInfo(
      info: TonApi.LiteServerMessageInfo
  ): (
      MessageType,                // message type
      Option[(Int, Array[Byte])], // src
      Option[(Int, Array[Byte])], // dst
      Option[Boolean],            // bounce
      Option[Boolean],            // bounced
      Option[Long],               // created_lt
      Option[Int],                // created_at
      Option[BigDecimal],         // import_fee
      Option[BigDecimal],         // ihr_fee
      Option[BigDecimal],         // fwd_fee
      Option[BigDecimal]          // value
  ) =
    info match {
      case extInt: LiteServerMessageInfoExtIn  =>
        val srcAddress               = getAddress(extInt.src)
        val (workchain, destAddress) = getAccount(extInt.dest)
        (
          MessageType.ExternalIn,
          srcAddress.map((workchain, _)),
          Some((workchain, destAddress)),
          None,
          None,
          None,
          None,
          bytesToBigDecimalSafe(extInt.importFee),
          None,
          None,
          None
        )
      case extOut: LiteServerMessageInfoExtOut =>
        val (workchain, srcAddress) = getAccount(extOut.src)
        val destAddress             = getAddress(extOut.dest)
        (
          MessageType.ExternalOut,
          Some((workchain, srcAddress)),
          destAddress.map((workchain, _)),
          None,
          None,
          Option(extOut.createdLt),
          Option(extOut.createdAt),
          None,
          None,
          None,
          None
        )
      case int: LiteServerMessageInfoInt       =>
        val (srcWorkchain, srcAddress)   = getAccount(int.src)
        val (destWorkchain, destAddress) = getAccount(int.dest)
        (
          MessageType.Internal,
          Some((srcWorkchain, srcAddress)),
          Some((destWorkchain, destAddress)),
          Option(int.bounce),
          Option(int.bounced),
          Option(int.createdLt),
          Option(int.createdAt),
          None,
          bytesToBigDecimalSafe(int.ihrFee),
          bytesToBigDecimalSafe(int.fwdFee),
          bytesToBigDecimalSafe(int.value)
        )
    }

  private def createDbStakeTransaction(tx: TonApi.LiteServerTransaction): Option[DBStakeTransaction] =
    tx.desc match {
      case descr: LiteServerTransactionDescrOrdinary if descr.additional != null =>
        val income  = msgValueSafe(tx.inMsg).getOrElse(BigDecimal(0))
        val outcome = tx.outMsgs.flatMap(msgValueSafe).sum

        val (_, inMsgFrom, _, _, _, _, _, _, _, _, _) = Option(tx.inMsg) match {
          case Some(inMsg) => extractMessageInfo(inMsg.info)
          case _           => (MessageType.Internal, None, None, None, None, None, None, None, None, None, None)
        }

        (descr.additional, inMsgFrom) match {
          case (additional: LiteServerTransactionAdditionalInfoStakeSend, (Some((workchain, address))))
              if additional.status == -1 =>
            Some(
              DBStakeTransaction(
                electorWorkchain = tx.workchain,
                electorAccountId = tx.account,
                transactionHash = tx.hash,
                transactionLt = tx.lt,
                transactionType = DBStakeTransactionType.Send,
                accountWorkchain = workchain,
                accountAddress = address,
                value = income - outcome,
                time = tx.now,
                electionTime = Some(additional.electTime),
                publicKey = Some(additional.pubkey),
                adnl = Some(additional.adnlAddr),
                maxFactor = Some(additional.maxFactor)
              )
            )
          case (additional: LiteServerTransactionAdditionalInfoStakeRecover, (Some((workchain, address))))
              if additional.success =>
            Some(
              DBStakeTransaction(
                electorWorkchain = tx.workchain,
                electorAccountId = tx.account,
                transactionHash = tx.hash,
                transactionLt = tx.lt,
                transactionType = DBStakeTransactionType.Recover,
                accountWorkchain = workchain,
                accountAddress = address,
                value = outcome - income,
                time = tx.now,
                electionTime = None,
                publicKey = None,
                adnl = None,
                maxFactor = None
              )
            )
          case _ => None
        }
      case _                                                                     => None
    }

  private def createDbBlock(block: TonApi.LiteServerBlock) =
    DBBlock(
      workchain = block.id.workchain,
      shard = block.id.shard,
      seqno = block.id.seqno,
      roothash = block.id.rootHash,
      filehash = block.id.fileHash,
      keyBlock = block.info.keyBlock,
      time = block.info.genUtime,
      transactionsCount = block.transactions.length
    )

}
