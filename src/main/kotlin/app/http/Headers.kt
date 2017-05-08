package app.http

import java.util.*

fun List<HttpHeader>.listHeaders() = joinToString(separator = "\r\n", prefix = "", postfix = "\r\n")

fun List<HttpHeader>.getHeader(fieldName: String) = find { it.fieldName.equals(fieldName, true) }?.fieldValue
        ?: throw NoSuchElementException("Header not found.")

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
data class HttpHeader(val fieldName: String, val fieldValue: String) {
    override fun toString(): String {
        return "$fieldName:$fieldValue"
    }
}

class Headers {
    private val headers = mutableListOf<HttpHeader>()

    fun addHeader(line: String) {
            val fieldName = line.substringBefore(":").trim()
            val fieldValue = line.substringAfter(":").trim()

//        If fieldName or fieldValue are the same length of the original, the token was not found
            if(fieldName.length == line.length || fieldValue.length == line.length)
                throw IllegalArgumentException("Invalid header format. ") // give info to user about correct format

            this.headers.add(HttpHeader(fieldName, fieldValue))

    }

    fun getHeaderList() = this.headers.toList()

}