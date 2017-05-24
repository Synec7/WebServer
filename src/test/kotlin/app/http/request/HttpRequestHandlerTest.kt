//import http.HttpMethod
//import http.HttpRequestHandler
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import java.lang.IllegalStateException
//
///**
// * Created by Vincente A. Campisi on 10/04/17.
// */
//
//class HttpRequestHandlerTest {
//
//    @Test(expected = IllegalArgumentException::class)
//    fun receiveBlankRequest() {
//        val requestString = ""
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler()
//        val request = rh.receiveRequest(requestStringAsStream)
//        assertEquals(HttpMethod.UNKNOWN, app.http.request.method)
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun receiveInvalidRequest() {
//        val requestString = " GET HTTP/1.1 localhost:60074\r\n" +
//                "content-type:application/json\r\n"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val app.http.request = rh.receiveRequest()
//        assertEquals(HttpMethod.GET, app.http.request.method)
//    }
//
//    @Test
//    fun receiveValidGetRequest() {
//        val requestString = "GET HTTP/1.1 localhost:60074\r\n" +
//                "content-type:application/json\r\n"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val app.http.request = rh.receiveRequest()
//        assertEquals(HttpMethod.GET, app.http.request.method)
//        assertEquals(1, app.http.request.headers.size)
//        assertEquals("application/json", app.http.request.headers.getHeader("content-type"))
//    }
//
//    @Test
//    fun receiveValidPostJsonRequest() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/json\r\n" +
//                "content-length: 7\r\n" +
//                "\r\n" +
//                "Example"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler.kt(requestStringAsStream)
//        val app.http.request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, app.http.request.method)
//        assertEquals(2, app.http.request.headers.size)
//        assertEquals("application/json", app.http.request.headers.getHeader("content-type"))
//        assert(app.http.request.body!!.toString(Charsets.ISO_8859_1).compareTo("Example") == 0)
//    }
//
//    @Test
//    fun receiveValidPostBinaryRequest() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/octet-stream\r\n" +
//                "content-length: 8\r\n" +
//                "\r\n" +
//                "65467896"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler.kt(requestStringAsStream)
//        val app.http.request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, app.http.request.method)
//        assertEquals(2, app.http.request.headers.size)
//        assertEquals("application/octet-stream", app.http.request.headers.getHeader("content-type"))
//        assert(app.http.request.body!!.toString(Charsets.ISO_8859_1).compareTo("65467896") == 0)
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun receivePostJsonWrongContentLength() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/json\r\n" +
//                "content-length: 8\r\n" +
//                "\r\n" +
//                "Example"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler.kt(requestStringAsStream)
//        val app.http.request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, app.http.request.method)
//        assertEquals(2, app.http.request.headers.size)
//        assertEquals("application/json", app.http.request.headers.getHeader("content-type"))
//        assert(app.http.request.body!!.toString(Charsets.ISO_8859_1).compareTo("Example") == 0)
//    }
//
//}
