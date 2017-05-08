package app.http.request

import app.http.Headers
import app.http.HttpHeader
import app.http.HttpMethod

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class HttpRequestReader(private val inputStream: java.io.InputStream) {

    fun readMethod(): HttpMethod = MethodValidator().validateMethod(readLine())

    fun readHeaders(): List<HttpHeader> {
        var line: String = readLine()
        val headers = Headers()

        while (line != "\r\n" && !line.isEmpty()) {
            headers.addHeader(line)
            line = readLine()
        }

        return headers.getHeaderList()
    }

    fun readBytes(numBytesToRead: Int, content: ByteArray) = this.inputStream.read(content, 0, numBytesToRead)

    private fun readLine(): String {
        val stringBuilder = StringBuilder()
        var inputValue = inputStream.read()

        while (inputValue != -1) {
            stringBuilder.append(inputValue.toChar())

            if (inputValue == '\n'.toInt()) {
                break
            }

            inputValue = inputStream.read()
        }

        return stringBuilder.toString()
    }
}