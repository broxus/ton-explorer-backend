package com.broxus.blockchainexplorer.ton

import com.broxus.blockchainexplorer.config.{ TonConfig, TonConfigProvider }
import com.broxus.ton.TonApi.{ FtabiParam, TonBlockIdExt, TonBlockIds }
import com.broxus.ton.{ TonApi, TonClient }
import zio.clock.Clock
import zio.logging.{ Logger, Logging }
import zio.{ Promise, Task, ZIO, ZLayer }

import scala.reflect.ClassTag

object TonService {
  trait Service {

    def getAccountState(address: String): Task[TonApi.FullAccountState]

    def unpackAccountAddress(address: String): Task[TonApi.UnpackedAccountAddress]

    def liteServerGetTime(): Task[TonApi.LiteServerCurrentTime]

    def liteServerGetMasterchainInfo(): Task[TonApi.LiteServerMasterchainInfo]

    def liteServerGetOneTransaction(
        blockId: TonApi.TonBlockIdExt,
        accountId: TonApi.LiteServerAccountId,
        lt: Long
    ): Task[TonApi.LiteServerTransaction]

    def liteServerGetAccountTransactions(
        accountId: TonApi.LiteServerAccountId,
        lt: Long,
        hash: Array[Byte],
        count: Int
    ): Task[TonApi.LiteServerTransactionList]

    def liteServerGetBlock(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerBlock]

    def liteServerGetBlockState(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerBlockState]

    def liteServerGetAccountState(
        blockId: TonApi.TonBlockIdExt,
        account: TonApi.LiteServerAccountId
    ): Task[TonApi.LiteServerAccount]

    def liteServerLookupBlock(blockId: TonApi.TonBlockId): Task[TonApi.LiteServerBlock]

    def liteServerGetConfigAll(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerConfigInfo]

    def liteServerGetBlockHeader(blockId: TonApi.TonBlockIdExt, mode: Int): Task[TonApi.LiteServerBlockHeader]

    def liteServerGetBlockProof(
        knownBlock: TonApi.TonBlockIdExt,
        targetBlock: TonApi.TonBlockIdExt,
        mode: Int
    ): Task[TonApi.LiteServerPartialBlockProof]

    def liteServerGetInfo(
        knownBlock: TonApi.TonBlockIdExt,
        targetBlock: TonApi.TonBlockIdExt,
        mode: Int
    ): Task[TonApi.LiteServerInfo]

    def liteServerBlockTransactions(
        blockId: TonBlockIdExt,
        after: Option[TonApi.LiteServerTransactionId3] = None
    ): ZIO[Any, Throwable, Set[TonApi.LiteServerTransaction]]

    def liteServerGetPastElections(
        blockId: TonBlockIdExt,
        electorAddr: Array[Byte]
    ): ZIO[Any, Throwable, TonApi.LiteServerPastElections]

    def smcLoad(address: String): ZIO[Any, Throwable, TonApi.SmcInfo]

    def smcGetCode(id: Long): ZIO[Any, Throwable, TonApi.TvmCell]

    def smcGetData(id: Long): ZIO[Any, Throwable, TonApi.TvmCell]

    def smcGetState(id: Long): ZIO[Any, Throwable, TonApi.TvmCell]

    def smcRunGetMethod(
        id: Long,
        method: TonApi.SmcMethodId,
        stack: Array[TonApi.TvmStackEntry]
    ): ZIO[Any, Throwable, TonApi.SmcRunResult]

    def ftabiComputeFunctionId(data: String): ZIO[Any, Throwable, TonApi.FtabiFunctionId]

    def ftabiComputeFunctionSignature(
        name: String,
        inputs: Array[FtabiParam],
        outputs: Array[FtabiParam]
    ): ZIO[Any, Throwable, TonApi.FtabiFunctionSignature]

    def ftabiComputeCreateFunction(
        name: String,
        header: Array[FtabiParam],
        inputs: Array[FtabiParam],
        outputs: Array[FtabiParam]
    ): ZIO[Any, Throwable, TonApi.FtabiFunction]

    def ftabiCreateMessageBody(
        function: TonApi.FtabiFunction,
        call: TonApi.FtabiFunctionCall
    ): ZIO[Any, Throwable, TonApi.FtabiMessageBody]

    def ftabiDecodeOutput(
        function: TonApi.FtabiFunction,
        data: Array[Byte]
    ): ZIO[Any, Throwable, TonApi.FtabiDecodedOutput]

    def ftabiDecodeInput(
        function: TonApi.FtabiFunction,
        data: Array[Byte],
        internal: Boolean
    ): ZIO[Any, Throwable, TonApi.FtabiDecodedInput]

    def computeLastBlockIds(topBlocks: Seq[TonApi.TonBlockId]): ZIO[Any, Throwable, TonBlockIds]
  }

  private val runtime = zio.Runtime.default

  def send[T <: TonApi.Object](client: TonClient, query: TonApi.Function)(implicit
      classTag: ClassTag[T]
  ): ZIO[Any, Throwable, T] =
    for {
      promise <- Promise.make[Throwable, T]
      _       <- Task(
                   client.send(
                     query,
                     { o: TonApi.Object =>
                       try o match {
                         case err: TonApi.Error                                                     =>
                           runtime.unsafeRunAsync_(promise.fail(TonApiErrorException(err)))
                         case response: TonApi.Object if classTag.runtimeClass.isInstance(response) =>
                           runtime.unsafeRunAsync_(promise.succeed(response.asInstanceOf[T]))
                         case unmatched                                                             =>
                           runtime.unsafeRunAsync_(
                             promise.fail(
                               TonApiUnmatchedResponse(classTag.runtimeClass.getSimpleName, unmatched.getClass.getSimpleName)
                             )
                           )
                       } catch {
                         case e: Exception =>
                           runtime.unsafeRunAsync_(promise.fail(e))
                       }
                     }
                   )
                 )
      result  <- promise.await
    } yield result

  val layer: ZLayer[Clock with Logging with TonConfigProvider, Throwable, TonService] = ZLayer.fromManaged(
    for {
      logger    <- ZIO.service[Logger[String]].toManaged_
      tonConfig <- ZIO.service[TonConfig].toManaged_
      client    <- (for {
                       client <- Task {
                                   TonClient.create(
                                     { case _: Any => () },
                                     { case _: Any => () },
                                     { case _: Any => () }
                                   )
                                 }
                       value  <- send[TonApi.OptionsInfo](
                                   client,
                                   new TonApi.Init(
                                     new TonApi.Options(
                                       new TonApi.Config(
                                         tonConfig.liteClient,
                                         tonConfig.blockchainName,
                                         tonConfig.useNetworkCallback,
                                         true
                                       ),
                                       new TonApi.KeyStoreTypeInMemory()
                                     )
                                   )
                                 )
                       _      <- send[TonApi.Ok](client, new TonApi.SetLogVerbosityLevel(tonConfig.verbosityLevel))
                       _      <- logger.info(value.toString)
                     } yield client).toManaged_

    } yield new Service {

      override def getAccountState(address: String): Task[TonApi.FullAccountState] =
        send[TonApi.FullAccountState](client, new TonApi.GetAccountState(new TonApi.AccountAddress(address)))

      override def unpackAccountAddress(address: String): Task[TonApi.UnpackedAccountAddress] =
        send[TonApi.UnpackedAccountAddress](client, new TonApi.UnpackAccountAddress(address))

      override def liteServerGetTime(): Task[TonApi.LiteServerCurrentTime] =
        send[TonApi.LiteServerCurrentTime](client, new TonApi.LiteServerGetTime())

      override def liteServerGetMasterchainInfo(): Task[TonApi.LiteServerMasterchainInfo] =
        send[TonApi.LiteServerMasterchainInfo](client, new TonApi.LiteServerGetMasterchainInfo())

      override def liteServerLookupBlock(blockId: TonApi.TonBlockId): Task[TonApi.LiteServerBlock] =
        send[TonApi.LiteServerBlock](client, new TonApi.LiteServerLookupBlock(1, blockId, 0, 0))

      override def liteServerGetConfigAll(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerConfigInfo] =
        send[TonApi.LiteServerConfigInfo](client, new TonApi.LiteServerGetConfigAll(blockId))

      override def liteServerGetOneTransaction(
          blockId: TonApi.TonBlockIdExt,
          accountId: TonApi.LiteServerAccountId,
          lt: Long
      ): Task[TonApi.LiteServerTransaction] =
        send[TonApi.LiteServerTransaction](
          client,
          new TonApi.LiteServerGetOneTransaction(
            blockId,
            accountId,
            lt
          )
        )

      override def liteServerGetAccountTransactions(
          accountId: TonApi.LiteServerAccountId,
          lt: Long,
          hash: Array[Byte],
          count: Int
      ): Task[TonApi.LiteServerTransactionList] =
        send[TonApi.LiteServerTransactionList](
          client,
          new TonApi.LiteServerGetTransactions(
            count,
            accountId,
            lt,
            hash
          )
        )

      override def liteServerGetBlock(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerBlock] =
        send[TonApi.LiteServerBlock](
          client,
          new TonApi.LiteServerGetBlock(blockId)
        )

      def liteServerGetBlockState(blockId: TonApi.TonBlockIdExt): Task[TonApi.LiteServerBlockState] =
        send[TonApi.LiteServerBlockState](
          client,
          new TonApi.LiteServerGetState(blockId)
        )

      override def liteServerGetAccountState(
          blockId: TonApi.TonBlockIdExt,
          account: TonApi.LiteServerAccountId
      ): Task[TonApi.LiteServerAccount] =
        send[TonApi.LiteServerAccount](client, new TonApi.LiteServerGetAccount(blockId, account))

      override def liteServerGetBlockHeader(
          blockId: TonApi.TonBlockIdExt,
          mode: Int
      ): Task[TonApi.LiteServerBlockHeader] =
        send[TonApi.LiteServerBlockHeader](
          client,
          new TonApi.LiteServerGetBlockHeader(blockId, mode)
        )

      override def liteServerGetBlockProof(
          knownBlock: TonApi.TonBlockIdExt,
          targetBlock: TonApi.TonBlockIdExt,
          mode: Int
      ): Task[TonApi.LiteServerPartialBlockProof] =
        send[TonApi.LiteServerPartialBlockProof](
          client,
          new TonApi.LiteServerGetBlockProof(
            mode,
            knownBlock,
            targetBlock
          )
        )

      override def liteServerGetInfo(
          knownBlock: TonApi.TonBlockIdExt,
          targetBlock: TonApi.TonBlockIdExt,
          mode: Int
      ): Task[TonApi.LiteServerInfo] =
        send[TonApi.LiteServerInfo](
          client,
          new TonApi.LiteServerGetInfo()
        )

      override def liteServerBlockTransactions(
          blockId: TonBlockIdExt,
          after: Option[TonApi.LiteServerTransactionId3] = None
      ): ZIO[Any, Throwable, Set[TonApi.LiteServerTransaction]] =
        for {
          response <- send[TonApi.LiteServerBlockTransactions](
                        client,
                        new TonApi.LiteServerListBlockTransactions(
                          blockId,
                          after.fold(7)(_ => 7 + 128),
                          1024,
                          after.orNull,
                          false,
                          false
                        )
                      )
          items    <- ZIO.foreachPar(response.ids.toSet) { id =>
                        liteServerGetOneTransaction(
                          blockId,
                          new TonApi.LiteServerAccountId(blockId.workchain, id.account),
                          id.lt
                        )
                      }
          next     <- if (response.incomplete && response.ids.length > 0) {
                        val last = response.ids.last
                        liteServerBlockTransactions(
                          blockId,
                          after = Some(new TonApi.LiteServerTransactionId3(last.account, last.lt))
                        )
                      } else
                        ZIO.succeed(Set.empty)
        } yield (items ++ next)

      override def liteServerGetPastElections(
          blockId: TonBlockIdExt,
          electorAddr: Array[Byte]
      ): ZIO[Any, Throwable, TonApi.LiteServerPastElections] =
        send[TonApi.LiteServerPastElections](client, new TonApi.LiteServerGetPastElections(blockId, electorAddr))

      override def smcLoad(address: String): ZIO[Any, Throwable, TonApi.SmcInfo] =
        send[TonApi.SmcInfo](client, new TonApi.SmcLoad(new TonApi.AccountAddress(address)))

      override def smcGetCode(id: Long): ZIO[Any, Throwable, TonApi.TvmCell] =
        send[TonApi.TvmCell](client, new TonApi.SmcGetCode(id))

      override def smcGetData(id: Long): ZIO[Any, Throwable, TonApi.TvmCell] =
        send[TonApi.TvmCell](client, new TonApi.SmcGetData(id))

      override def smcGetState(id: Long): ZIO[Any, Throwable, TonApi.TvmCell] =
        send[TonApi.TvmCell](client, new TonApi.SmcGetState(id))

      override def smcRunGetMethod(
          id: Long,
          method: TonApi.SmcMethodId,
          stack: Array[TonApi.TvmStackEntry]
      ): ZIO[Any, Throwable, TonApi.SmcRunResult] =
        send[TonApi.SmcRunResult](client, new TonApi.SmcRunGetMethod(id, method, stack))

      override def ftabiComputeFunctionId(data: String): ZIO[Any, Throwable, TonApi.FtabiFunctionId] =
        send[TonApi.FtabiFunctionId](client, new TonApi.FtabiComputeFunctionId(new TonApi.FtabiFunctionSignature(data)))

      override def ftabiComputeFunctionSignature(
          name: String,
          inputs: Array[FtabiParam],
          outputs: Array[FtabiParam]
      ): ZIO[Any, Throwable, TonApi.FtabiFunctionSignature] =
        send[TonApi.FtabiFunctionSignature](client, new TonApi.FtabiComputeFunctionSignature(name, inputs, outputs))

      override def ftabiComputeCreateFunction(
          name: String,
          header: Array[FtabiParam],
          inputs: Array[FtabiParam],
          outputs: Array[FtabiParam]
      ): ZIO[Any, Throwable, TonApi.FtabiFunction] =
        send[TonApi.FtabiFunction](client, new TonApi.FtabiCreateFunction(name, header, inputs, outputs))

      override def ftabiCreateMessageBody(
          function: TonApi.FtabiFunction,
          call: TonApi.FtabiFunctionCall
      ): ZIO[Any, Throwable, TonApi.FtabiMessageBody] =
        send[TonApi.FtabiMessageBody](client, new TonApi.FtabiCreateMessageBody(function, call))

      override def ftabiDecodeOutput(
          function: TonApi.FtabiFunction,
          data: Array[Byte]
      ): ZIO[Any, Throwable, TonApi.FtabiDecodedOutput] =
        send[TonApi.FtabiDecodedOutput](client, new TonApi.FtabiDecodeOutput(function, data))

      override def ftabiDecodeInput(
          function: TonApi.FtabiFunction,
          data: Array[Byte],
          internal: Boolean
      ): ZIO[Any, Throwable, TonApi.FtabiDecodedInput] =
        send[TonApi.FtabiDecodedInput](client, new TonApi.FtabiDecodeInput(function, data, internal))

      override def computeLastBlockIds(topBlocks: Seq[TonApi.TonBlockId]): ZIO[Any, Throwable, TonBlockIds] =
        send[TonApi.TonBlockIds](client, new TonApi.ComputeLastBlockIds(topBlocks.toArray))

    }
  )
}

case class TonApiErrorException(error: TonApi.Error)                 extends Exception {
  override def getMessage: String = s"TonAPI returns error: code = ${error.code}, message = ${error.message}"
}
case class TonApiUnmatchedResponse(expected: String, actual: String) extends Exception {
  override def getMessage: String = s"TonAPI unmatched response: expected $expected, actual = $actual"
}
