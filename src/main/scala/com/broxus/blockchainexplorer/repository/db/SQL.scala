package com.broxus.blockchainexplorer.repository.db

import cats.data.NonEmptyList
import cats.implicits._
import com.broxus.blockchainexplorer.repository.DBAccountColumn
import com.broxus.blockchainexplorer.repository.DBAccountColumn.DBAccountColumn
import com.broxus.blockchainexplorer.repository.models._
import doobie.{ Query0, _ }
import doobie.implicits._
import doobie.util.fragment.Fragment
import doobie.util.fragments._
import doobie.util.update.Update

object SQL {

  //user
  def upsertTgUser(tgUser: DBTgUser): Update0 =
    sql"""INSERT INTO
        tg_user(id, first_name, last_name, username, photo_url, last_auth_date, language_code) VALUES
      (${tgUser.id}, ${tgUser.firstName}, ${tgUser.lastName}, ${tgUser.username}, ${tgUser.photoUrl}, ${tgUser.lastAuthDate}, ${tgUser.languageCode})
      ON CONFLICT (id) DO UPDATE SET
          first_name = EXCLUDED.first_name,
          last_name = EXCLUDED.last_name,
          username = EXCLUDED.username,
          photo_url = EXCLUDED.photo_url,
          last_auth_date = EXCLUDED.last_auth_date""".update

  def findTgUser(id: Long): Query0[DBTgUser] = sql"""SELECT * FROM tg_user WHERE id = $id""".query[DBTgUser]

  //stake_transaction

  def listElectionStakes(electionId: Int): Query0[DBStakeTransaction] =
    sql"""
          SELECT * FROM stake_transaction
          WHERE
            transaction_type = 'send' AND
            election_time = $electionId
        """.query[DBStakeTransaction]

  def listAllStakeTransactions: Query0[DBStakeTransaction] =
    sql"""
         SELECT * FROM stake_transaction
         WHERE transaction_type = 'send'
         ORDER BY time
       """.query[DBStakeTransaction]

  def listStakeTransactions(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      electionTime: Option[Int]
  ): Query0[DBStakeTransaction] = {
    val fromFr         = fromTs.map(from => fr"time >= $from")
    val toFr           = toTs.map(to => fr"time < $to")
    val electionTimeFr = electionTime.map(time => fr"election_time = $time")
    val accountFr      = account.map {
      case (workchain, address) => fr"account_workchain = $workchain AND account_address = $address"
    }
    val q              =
      fr"""SELECT * FROM stake_transaction """ ++
        whereAndOpt(fromFr, toFr, accountFr, electionTimeFr) ++
        fr"""ORDER BY time DESC 
             LIMIT $limit 
             OFFSET $offset"""

    q.query[DBStakeTransaction]
  }

  def countStakeTransactions(
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      electionTime: Option[Int]
  ): Query0[Long] = {
    val fromFr         = fromTs.map(from => fr"time >= $from")
    val toFr           = toTs.map(to => fr"time < $to")
    val electionTimeFr = electionTime.map(time => fr"election_time = $time")
    val accountFr      = account.map {
      case (workchain, address) => fr"account_workchain = $workchain AND account_address = $address"
    }
    val q              =
      fr"""SELECT COUNT(*) FROM stake_transaction """ ++ whereAndOpt(fromFr, toFr, accountFr, electionTimeFr)

    q.query[Long]
  }

  def elections(fromTs: Option[Int], toTs: Option[Int]): Query0[Int] = {
    val fromFr = fromTs.map(from => fr"election_time >= $from")
    val toFr   = toTs.map(to => fr"election_time < $to")
    val q      =
      fr"""SELECT DISTINCT election_time FROM stake_transaction """ ++
        whereAndOpt(Some(fr"election_time IS NOT NULL"), fromFr, toFr) ++
        fr"""ORDER BY election_time ASC"""

    q.query[Int]
  }

  def getAccountsByPublicKeys(publicKeys: NonEmptyList[Array[Byte]]): Query0[(Array[Byte], Int, Array[Byte])] = {
    val q = fr"""SELECT
              public_key,
              account_workchain,
              account_address
            FROM
              stake_transaction
            WHERE""" ++ Fragments.in(fr"public_key", publicKeys)
    q.query[(Array[Byte], Int, Array[Byte])]
  }

  def updateStakeTransactionAndlAndMaxFactor(item: DBStakeTransaction): Update0 =
    sql"""UPDATE 
              stake_transaction 
            SET 
              adnl = ${item.adnl}, 
              max_factor = ${item.maxFactor} 
            WHERE 
              elector_workchain = ${item.electorWorkchain} AND
              elector_account_id = ${item.electorAccountId} AND 
              transaction_hash = ${item.transactionHash} AND 
              transaction_lt = ${item.transactionLt}""".update

  def nextElectionsWithEmptyMaxFactor =
    sql"""SELECT MIN(election_time) FROM stake_transaction
        WHERE transaction_type = 'send' AND (adnl IS NULL OR max_factor IS NULL)""".query[Int]

  // accounts

  def upsertAccounts(items: List[DBAccount]): doobie.ConnectionIO[Int] =
    Update[DBAccount]("""INSERT INTO
      account(workchain, address, balance, created, updated) VALUES 
      (?, ?, ?, ?, ?)
      ON CONFLICT (workchain, address) DO NOTHING""").updateMany(items)

  def listAccounts(
      limit: Int,
      offset: Long,
      orderColumn: DBAccountColumn,
      asc: Boolean,
      fromTs: Option[Int],
      toTs: Option[Int]
  ): Query0[DBAccount] = {
    val fromFr           = fromTs.map(from => fr"${orderColumn.toString} >= $from")
    val toFr             = toTs.map(to => fr"${orderColumn.toString} < $to")
    val orderDirectionFr = if (asc) fr"""ASC""" else fr"""DESC"""
    val orderFr          = orderColumn match {
      case (DBAccountColumn.created) => fr"""ORDER BY created """ ++ orderDirectionFr
      case (DBAccountColumn.updated) => fr"""ORDER BY updated """ ++ orderDirectionFr
      case (DBAccountColumn.balance) => fr"""ORDER BY balance """ ++ orderDirectionFr
    }
    val q                =
      fr"""SELECT * FROM account """ ++
        whereAndOpt(fromFr, toFr) ++
        orderFr ++
        fr""" LIMIT $limit OFFSET $offset"""

    q.query[DBAccount]
  }

  def totalSupply(): Query0[BigDecimal] =
    sql"""SELECT SUM(balance) FROM account""".query[BigDecimal]

  def accountsCreated(fromTs: Option[Int]): Query0[Long] = {
    val baseQ  = fr"""SELECT COUNT(*) FROM account """
    val fromFr = fromTs.map(from => fr"created > $from")
    (baseQ ++ whereAndOpt(fromFr)).query[Long]
  }
  def countUpdated(fromTs: Option[Int]): Query0[Long] = {
    val baseQ  = fr"""SELECT COUNT(*) FROM account """
    val fromFr = fromTs.map(from => fr"updated > $from")
    (baseQ ++ whereAndOpt(fromFr)).query[Long]
  }

  // stake transactions

  def upsertStakeTransactions(items: List[DBStakeTransaction]): doobie.ConnectionIO[Int] =
    Update[DBStakeTransaction]("""INSERT INTO  stake_transaction(
              elector_workchain, elector_account_id, transaction_hash, transaction_lt, transaction_type, 
              account_workchain, account_address, value, time, election_time, 
              public_key, adnl, max_factor) 
            VALUES 
              (?, ?, ?, ?, ?, 
               ?, ?, ?, ?, ?, 
               ?, ?, ?) 
            ON CONFLICT (elector_workchain, elector_account_id, transaction_hash, transaction_lt) DO NOTHING
      """).updateMany(items)

  // block

  def blocksCount(fromTs: Option[Int]): Query0[(Int, Long, Long)] = {
    val baseQ   = fr"""SELECT workchain, shard, COUNT(seqno) FROM block """
    val fromFr  = fromTs.map(from => fr"time >= $from")
    val groupBy = fr""" GROUP BY workchain, shard"""
    (baseQ ++ whereAndOpt(fromFr) ++ groupBy).query[(Int, Long, Long)]
  }

  def maxSeqno(): Query0[Int] =
    sql"""SELECT MAX(seqno) FROM block """.query[Int]

  def getLatestIndexed(): Query0[(Int, Long, Int)] =
    sql"""SELECT 
            workchain, 
            shard, 
            MAX(seqno) 
          FROM 
            block 
          GROUP BY  
            workchain,
            shard
      """.query[(Int, Long, Int)]

  def upsertBlocks(items: List[DBBlock]): doobie.ConnectionIO[Int] =
    Update[DBBlock]("""INSERT INTO 
              block(workchain, shard, seqno, roothash, filehash, 
                    key_block, time, transactions_count) 
            VALUES 
              (?, ?, ?, ?, ?, 
               ?, ?, ?) 
            ON CONFLICT (workchain, shard, seqno) DO NOTHING
      """).updateMany(items)

  def searchBlockBySeqno(seqno: Int): Query0[DBBlock] =
    sql"""SELECT 
              * 
            FROM 
              block 
            WHERE 
              seqno = $seqno
      """.query[DBBlock]

  def searchBlockByFullSeqno(workchain: Int, shard: Long, seqno: Int): Query0[DBBlock] =
    sql"""SELECT
            *
           FROM
             block
           WHERE
             workchain = $workchain AND
             shard = $shard AND
             seqno = $seqno
       """.query[DBBlock]

  def searchBlockByHash(hash: Array[Byte]): Query0[DBBlock] =
    sql"""SELECT 
              * 
            FROM 
              block 
            WHERE 
              (roothash = $hash OR filehash = $hash)
       """.query[DBBlock]

  def listBlocks(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      skipEmpty: Boolean
  ): Query0[DBBlock] = {
    val fromFr     = fromTs.map(from => fr"b.time >= $from")
    val toFr       = toTs.map(to => fr"b.time < $to")
    val isNotEmpty = Option.when(skipEmpty)(fr"b.transactions_count > 0")
    val q          =
      fr"""SELECT * FROM block b """ ++
        whereAndOpt(fromFr, toFr, isNotEmpty) ++
        fr"""ORDER BY b.time DESC
             LIMIT $limit
             OFFSET $offset"""

    q.query[DBBlock]
  }

  // transaction
  def upsertTransactions(items: List[DBTransaction]): doobie.ConnectionIO[Int] =
    Update[DBTransaction]("""INSERT INTO 
              transaction(workchain, account_id, hash, lt, block_shard, 
                          block_seqno, transaction_type, balance_change, time, total_fees, 
                          storage_fees_collected, storage_fees_due, due_fees_collected, gas_fees, total_fwd_fees, 
                          total_action_fees, req_fwd_fees, msg_fees, fwd_fees, aborted, 
                          destroyed, credit_first, is_tock) 
            VALUES (?, ?, ?, ?, ?, 
                    ?, ?, ?, ?, ?, 
                    ?, ?, ?, ?, ?, 
                    ?, ?, ?, ?, ?, 
                    ?, ?, ?) 
            ON CONFLICT (workchain, account_id, hash, lt) DO NOTHING
      """).updateMany(items)

  def searchTransactionByHash(hash: Array[Byte]): Query0[(DBTransaction, DBBlock)] =
    sql"""SELECT 
              * 
            FROM 
                transaction t
              INNER JOIN 
                  block b 
                ON 
                  t.workchain = b.workchain 
                  AND t.block_shard = b.shard 
                  AND t.block_seqno = b.seqno 
          WHERE 
            t.hash = $hash
      """.query[(DBTransaction, DBBlock)]

  def listTransactions(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]],
      transactionTypes: Option[Seq[String]]
  ): Query0[DBTransaction] = {
    val fromFr           = fromTs.map(from => fr"time >= $from")
    val toFr             = toTs.map(to => fr"time < $to")
    val typesFr          = transactionTypes
      .map(_.filter(e => TransactionType.fromEnum(e).nonEmpty).mkString("transaction_type IN ('", "','", "')"))
      .map(f => Fragment(f, Nil))
    val accountFr        = account.map { case (workchain, address) => fr"workchain = $workchain AND account_id = $address" }
    val excludeAccountFr = excludeAccounts
      .map(_.map {
        case (workchain, address) =>
          fr" NOT (workchain = $workchain AND account_id = $address) "
      })
      .map(frs => and(frs: _*))
    val q                =
      fr"""SELECT * FROM transaction """ ++
        whereAndOpt(fromFr, toFr, accountFr, typesFr, excludeAccountFr) ++
        fr"""ORDER BY time DESC 
             LIMIT $limit 
             OFFSET $offset"""

    q.query[DBTransaction]
  }

  def transactionsCount(
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]],
      transactionTypes: Option[Seq[String]]
  ): Query0[Long] = {
    val fromFr           = fromTs.map(from => fr"time >= $from")
    val toFr             = toTs.map(to => fr"time < $to")
    val typesFr          = transactionTypes
      .map(_.filter(e => TransactionType.fromEnum(e).nonEmpty).mkString("transaction_type IN ('", "','", "')"))
      .map(types => Fragment(types, Nil))
    val accountFr        = account.map { case (workchain, address) => fr"workchain = $workchain AND account_id = $address" }
    val excludeAccountFr = excludeAccounts
      .map(_.map {
        case (workchain, address) =>
          fr" NOT (workchain = $workchain AND account_id = $address) "
      })
      .map(frs => and(frs: _*))

    val q = fr"""SELECT COUNT(hash) FROM transaction """ ++
      whereAndOpt(fromFr, toFr, accountFr, typesFr, excludeAccountFr)

    q.query[Long]
  }

  // message

  def volume(fromTs: Option[Int]): Query0[BigDecimal] = {
    val baseQ  = fr"""SELECT SUM(value) FROM message """
    val fromFr = fromTs.map(from => fr"transaction_time >= $from")
    (baseQ ++ whereAndOpt(Some(fr"NOT out"), fromFr)).query[BigDecimal]
  }

  def upsertMessages(items: List[DBMessage]): doobie.ConnectionIO[Int] =
    Update[DBMessage]("""INSERT INTO 
              message(body_hash, out, n, transaction_workchain, transaction_account_id, 
                      transaction_hash, transaction_lt, message_type, transaction_time, value, 
                      ihr_fee, fwd_fee, import_fee, src_workchain, src_address, 
                      dst_workchain, dst_address, bounce, bounced, created_lt, 
                      created_at) 
            VALUES (?, ?, ?, ?, ?, 
                    ?, ?, ?, ?, ?,
                    ?, ?, ?, ?, ?,
                    ?, ?, ?, ?, ?, 
                    ?) 
            ON CONFLICT (body_hash, out, n, 
                      transaction_workchain, transaction_account_id, 
                      transaction_hash, transaction_lt) DO NOTHING""").updateMany(items)

  def searchMessageByHash(hash: Array[Byte], limit: Int): Query0[(DBMessage, DBTransaction, DBBlock)] =
    sql"""SELECT 
              * 
            FROM 
                message m 
              INNER JOIN 
                  transaction t
                ON
                  m.transaction_workchain = t.workchain
                  AND m.transaction_account_id = t.account_id
                  AND m.transaction_hash = t.hash
                  AND m.transaction_lt = t.lt
              INNER JOIN 
                  block b 
                ON 
                  t.workchain = b.workchain 
                  AND t.block_shard = b.shard 
                  AND t.block_seqno = b.seqno 
          WHERE 
            m.body_hash = $hash
          ORDER BY 
            m.transaction_time DESC
          LIMIT $limit   
      """.query[(DBMessage, DBTransaction, DBBlock)]

  def messagesCount(
      fromTs: Option[Int],
      toTs: Option[Int],
      out: Option[Boolean],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]]
  ): Query0[Long] = {
    val fromFr           = fromTs.map(from => fr"transaction_time >= $from")
    val toFr             = toTs.map(to => fr"transaction_time < $to")
    val outFr            = out.map(o => fr"out = $o")
    val accountFr        = account.map {
      case (workchain, address) =>
        fr"transaction_workchain = $workchain AND transaction_account_id = $address"
    }
    val excludeAccountFr = excludeAccounts
      .map(_.map {
        case (workchain, address) =>
          fr" NOT (transaction_workchain = $workchain AND transaction_account_id = $address) "
      })
      .map(frs => and(frs: _*))

    val q = fr"""SELECT COUNT(*) FROM message """ ++ whereAndOpt(fromFr, toFr, accountFr, outFr, excludeAccountFr)

    q.query[Long]
  }

  def listMessages(
      limit: Int,
      offset: Long,
      fromTs: Option[Int],
      toTs: Option[Int],
      account: Option[(Int, Array[Byte])],
      excludeAccounts: Option[Seq[(Int, Array[Byte])]]
  ): Query0[DBMessage] = {
    val fromFr           = fromTs.map(from => fr"transaction_time >= $from")
    val toFr             = toTs.map(to => fr"transaction_time < $to")
    val accountFr        = account.map {
      case (workchain, address) =>
        fr"transaction_workchain = $workchain AND transaction_account_id = $address"
    }
    val excludeAccountFr = excludeAccounts
      .map(_.map {
        case (workchain, address) =>
          fr" NOT (transaction_workchain = $workchain AND transaction_account_id = $address) "
      })
      .map(frs => and(frs: _*))
    val q                =
      fr"""SELECT * FROM message """ ++
        whereAndOpt(fromFr, toFr, accountFr, excludeAccountFr) ++
        fr"""ORDER BY transaction_time DESC 
             LIMIT $limit 
             OFFSET $offset"""

    q.query[DBMessage]
  }
}
