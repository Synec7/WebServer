package http

/**
* Created by Vincente A. Campisi on 31/03/17.
*/
enum class HttpStatusCode(val code: Int, val description: String) {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad app.http.request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented")
}