package request

import header.HttpHeader
import header.getHeader
import method.HttpMethod
import java.io.InputStream

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class RequestHandler(input: InputStream) {
    private val requestReader = RequestReader(input)

    fun receiveRequest(): Request {
        val request = Request(requestReader.readMethod(), requestReader.readHeaders())

        if (request.method == HttpMethod.POST) {
            try {
                receiveRequestBody(request.headers.getHeader("content-length").toInt(), request)
            } catch (nfe: NumberFormatException) {
                throw IllegalArgumentException("Invalid header format.")
            }
        }
        return request
    }

    private fun receiveRequestBody(contentLength: Int, request: Request) {
        if (contentLength <= 0) {
            throw IllegalStateException("Invalid content-length header.")
        }

        val requestBody = ByteArray(contentLength)
        val bytes = this.requestReader.readBytes(contentLength, requestBody)

        if (bytes != contentLength) {
            throw IllegalStateException("Content Length does not match number of bytes read.")
        }

        request.body = requestBody
    }
}

class Request(val method: HttpMethod, val headers: List<HttpHeader>, var body: ByteArray? = null)