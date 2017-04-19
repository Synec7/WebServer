package core

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */
interface IWebServer: Runnable {

    fun start() {}
    fun stop() {}
    override fun run() {
        start()
    }
}