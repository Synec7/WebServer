package core

import java.util.concurrent.ExecutorService

/**
 * Created by Vincente A. Campisi on 03/05/17.
 */
interface Server {

    fun listen()
    fun shutdown()
    fun getRuntime(): Runtime
    fun getExecutor(): ExecutorService
}