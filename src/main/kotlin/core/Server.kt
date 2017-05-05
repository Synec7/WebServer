package core

import mu.KLogging
import org.apache.log4j.BasicConfigurator
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */

class Server(private val socket: ServerSocket,
             private val requestHandler: IRequestHandler,
             private val responseHandler: IResponseHandler) : IServer {

    companion object : KLogging()

    private val executor = Executors.newFixedThreadPool(5)
    val clientConnections = mutableListOf<Socket>()

    init {
        BasicConfigurator.configure()
    }

    override fun listen() =
            try {
                logger.info("Server listening on port ${socket.localPort}).")

                while (true) {
                    val clientSocket = socket.accept()
                    clientConnections.add(clientSocket)

                    val clientSession = ClientSession(
                            clientSocket,
                            requestHandler,
                            responseHandler
                    )
                    executor.submit(clientSession)
                    logger.info("New client connection accepted on Socket ${clientSocket.port}")
                }
            } catch (re: RuntimeException) {
                logger.error("Socket closed, server shutting down...")
                executor.shutdown()
                logger.info("Server shut down complete.")
            } catch (se: SocketException) {
                logger.error { "$se.message\nServer shutting down..." }
                shutdown()
            }


    override fun shutdown() =
            try {
                this.executor.shutdown()
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    for (s: Socket in clientConnections)
                        s.close()
                }
                socket.close()
            } catch (ignoredException: Exception) {
            }

    override fun getRuntime(): Runtime = Runtime.getRuntime()
    override fun getExecutor(): ExecutorService = this.executor

}