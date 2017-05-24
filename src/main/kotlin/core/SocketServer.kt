package core

import core.protocol.Protocol
import mu.KLogging
import org.apache.log4j.BasicConfigurator
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import java.util.concurrent.Executors

/**
* Created by Vincente A. Campisi on 20/03/17.
*/

class SocketServer(private val socket: ServerSocket,
                   private val protocol: Protocol) : Server {

	companion object : KLogging()

	private val executor = Executors.newFixedThreadPool(5)
	private val clientConnections = mutableListOf<Socket>()

	init {
		BasicConfigurator.configure()
	}

	override fun listen() =
			try {
				logger.info("SocketServer listening on port ${socket.localPort}).")

				while (true) {
					val clientSocket = socket.accept()
					clientConnections.add(clientSocket)

					val clientSession = ClientSession(clientSocket, protocol)
					executor.submit(clientSession)
					logger.info("New client connection accepted on Socket ${clientSocket.port}")
				}
			} catch (re: RuntimeException) {
				logger.error("${re.message}\nSocketServer shutting down...")
				shutdown()
				logger.info("SocketServer shut down complete.")
			} catch (se: SocketException) {
				logger.error { "${se.message}\nSocketServer shutting down..." }
				shutdown()
				logger.info("SocketServer shut down complete.")
			}


	override fun shutdown() =
			try {
				for (s: Socket in clientConnections)
					s.close()
				this.executor.shutdownNow()
				socket.close()
			} catch (ignoredException: Exception) {
			} finally {
				socket.close()
			}

}