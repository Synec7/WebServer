package request

import header.HeaderBuilder
import header.HttpHeader
import method.HttpMethod
import method.MethodValidator
import java.io.InputStream

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class RequestReader(private val inputStream: InputStream) {

    fun readMethod(): HttpMethod = MethodValidator().validateMethod(readLine())

    fun readHeaders(): List<HttpHeader> {
        var line: String = readLine()
        val headers = HeaderBuilder()

        while (line != "\r\n" && !line.isEmpty()) {
            headers.addHeader(line)
            line = readLine()
        }

        return headers.getHeaderReader()
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