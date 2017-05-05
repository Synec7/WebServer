package app

import core.IRequest
import core.IResponse
import core.IResponseHandler
import http.HttpMethod
import http.getHeader

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 07/04/17.
 */
class HttpResponseHandler : IResponseHandler {

    override fun buildResponse(httpRequest: IRequest): IResponse =

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

    override fun sendResponse(socket: Socket, httpResponse: IResponse) {
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