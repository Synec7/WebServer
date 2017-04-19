package response

import header.getHeader
import method.HttpMethod
import request.Request
import java.io.OutputStream

/**
 * Created by Vincente A. Campisi on 07/04/17.
 */
class ResponseHandler(private val output: OutputStream) {

    fun buildResponse(request: Request): Response =

            when (request.method) {
                HttpMethod.POST -> buildPost(request.headers.getHeader("Content-Type"), request.body)
                HttpMethod.UNKNOWN -> BadRequestResponse()
                else -> {
                    NotImplementedResponse()
                }
            }

    fun sendResponse(response: Response) {
        this.output.write("${response.getResponseText()}\r\n".toByteArray(Charsets.ISO_8859_1))
        response.responseBody?.let {
            this.output.write(response.responseBody)
        }

        this.output.flush()
    }

    private fun buildPost(contentType: String, body: ByteArray?): Response =
            when (contentType) {
                "application/octet-stream" -> BinaryResponse(body)
                "application/json" -> JsonResponse(body)
                else -> {
                    BadRequestResponse()
                }
            }

}