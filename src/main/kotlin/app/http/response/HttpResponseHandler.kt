package app.http.response

import app.http.HttpMethod
import app.http.getHeader
import app.http.request.HttpRequest
import core.Request
import core.Response
import core.ResponseHandler
import java.net.Socket

/**
 * Created by Vincente A. Campisi on 07/04/17.
 */
class HttpResponseHandler : ResponseHandler {

    override fun buildResponse(httpRequest: Request): Response =

            when ((httpRequest as HttpRequest).method) {
                HttpMethod.POST -> buildPost(httpRequest.headers.getHeader("Content-Type"), httpRequest.body)
                HttpMethod.UNKNOWN -> BadRequestHttpResponse()
                else -> {
                    NotImplementedHttpResponse()
                }
            }

    override fun sendDefaultResponse(socket: Socket) {
        sendResponse(socket, BadRequestHttpResponse())
    }

    override fun sendResponse(socket: Socket, httpResponse: Response) {
        val output = socket.getOutputStream()
        output.write("${httpResponse.getResponseText()}\r\n".toByteArray(Charsets.ISO_8859_1))
        (httpResponse as HttpResponse).responseBody?.let {
            output.write(httpResponse.responseBody)
        }

        output.flush()
    }

    private fun buildPost(contentType: String, body: ByteArray?): HttpResponse =
            when (contentType) {
                "application/octet-stream" -> BinaryHttpResponse(body)
                "application/json" -> JsonHttpResponse(body)
                else -> {
                    BadRequestHttpResponse()
                }
            }

}