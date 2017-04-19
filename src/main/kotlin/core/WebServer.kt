package core

import java.net.ServerSocket
import java.net.SocketException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */

class WebServer(private val serverSocket: ServerSocket) : IWebServer {

    private val executor = Executors.newFixedThreadPool(5)

    override fun start() {
        try {
            addShutdownHook()
            println("Server started at http://localhost:${serverSocket.localPort})")

            while (true) {
                executor.submit(WebClient(serverSocket.accept()))
            }
        } catch (re: RuntimeException) {
            println("Socket closed, server shutting down...")
            executor.shutdown()
            println("Server shut down complete.")
        } catch (se: SocketException) {
            println("Server shutting down...")
            stop()
        }
    }

    override fun stop() {
        try {
            this.executor.shutdown()
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                val dropped: List<Runnable> = executor.shutdownNow()
                println("${dropped.size} tasks not executed.")

            }
            serverSocket.close()
        } catch (ignoredException: Exception) {
        }
    }

    fun addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(Thread(WebServerShutdownHook(this.executor)))
    }

}