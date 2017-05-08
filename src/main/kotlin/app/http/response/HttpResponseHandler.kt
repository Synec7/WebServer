package app.http.response

import app.http.getHeader
import app.http.request.HttpRequest

/**
 * Created by Vincente A. Campisi on 07/04/17.
 */
class HttpResponseHandler : core.ResponseHandler {

    override fun buildResponse(httpRequest: core.Request): core.Response =

            when ((httpRequest as HttpRequest).method) {
                app.http.HttpMethod.POST -> buildPost(httpRequest.headers.getHeader("Content-Type"), httpRequest.body)
                app.http.HttpMethod.UNKNOWN -> app.http.response.BadRequestHttpResponse()
                else -> {
                    app.http.response.NotImplementedHttpResponse()
                }
            }

    override fun sendDefaultResponse(socket: java.net.Socket) {
        sendResponse(socket, app.http.response.BadRequestHttpResponse())
    }

    override fun sendResponse(socket: java.net.Socket, httpResponse: core.Response) {
        val output = socket.getOutputStream()
        output.write("${httpResponse.getResponseText()}\r\n".toByteArray(Charsets.ISO_8859_1))
        (httpResponse as HttpResponse).responseBody?.let {
            output.write(httpResponse.responseBody)
        }

        output.flush()
    }

    private fun buildPost(contentType: String, body: ByteArray?): HttpResponse =
            when (contentType) {
                "application/octet-stream" -> app.http.response.BinaryHttpResponse(body)
                "application/json" -> app.http.response.JsonHttpResponse(body)
                else -> {
                    app.http.response.BadRequestHttpResponse()
                }
            }

}