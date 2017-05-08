package app.http.request

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class HttpRequestReader(private val inputStream: java.io.InputStream) {

    fun readMethod(): app.http.HttpMethod = MethodValidator().validateMethod(readLine())

    fun readHeaders(): List<app.http.HttpHeader> {
        var line: String = readLine()
        val headers = app.http.Headers()

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