package response

import header.HeaderBuilder

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
interface Response {
    val responseText: StringBuilder
    val headers: HeaderBuilder
    val httpVersion: String
    val responseBody: ByteArray?

    fun getResponseText(): String
}