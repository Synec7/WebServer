package core

import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by Vincente A. Campisi on 27/03/17.
 */
class WebServerShutdownHook(private val executor: ExecutorService) : Runnable {
    override fun run() {
        this.executor.shutdown()

        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            val dropped: List<Runnable> = executor.shutdownNow()
            println("${dropped.size} tasks not executed.")
        }

        println("Server shut down complete.")
    }
}