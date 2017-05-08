package core

import java.net.Socket
import java.util.concurrent.ExecutorService

/**
 * Created by Vincente A. Campisi on 27/03/17.
 */
class ServerShutdownHook(private val executor: ExecutorService, private val clientConnections: MutableList<Socket>) : Runnable {
//    override fun run() {
//        val logger = SocketServer.logger
//        this.executor.shutdown()
//
//        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
//            val dropped: List<Runnable> = executor.shutdownNow()
//            logger.info("${dropped.size} tasks not executed.")
//        }
//
//        logger.info("SocketServer shut down complete.")
//    }

    override fun run() {
        try {
            val logger = SocketServer.logger
            for (s: Socket in clientConnections)
                s.close()
            logger.info("SocketServer shut down complete.")
        } catch (ignoredException: Exception) {
        }
    }
}