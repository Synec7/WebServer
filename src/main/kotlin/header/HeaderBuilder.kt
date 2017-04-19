package header

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

class HeaderBuilder {
    private val headers = mutableListOf<HttpHeader>()

    fun addHeader(line: String) {
        try {
            if (line.length == 0) {
                throw IndexOutOfBoundsException("Invalid header format.")
            }
            val fieldName = line.substringBefore(":").trim()
            val fieldValue = line.substringAfter(":").trim()

            this.headers.add(HttpHeader(fieldName, fieldValue))
        } catch (iob: IndexOutOfBoundsException) {
            throw IllegalArgumentException("Invalid header format.")
        }
    }

    fun getHeaderReader() = this.headers.toList()

}