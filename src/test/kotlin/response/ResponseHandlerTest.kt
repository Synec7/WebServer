//package response
//
//import junit.framework.Assert.assertEquals
//import method.HttpMethod
//import org.junit.Before
//import org.junit.Test
//
///**
// * Created by Vincente A. Campisi on 10/04/17.
// */
//class ResponseHandlerTest {
//
//    lateinit var rh: ResponseHandler
//
//    @Before
//    fun setup() {
//        this.rh = ResponseHandler(System.out)
//    }
//
//    @Test
//    fun buildUnknownResponse() {
//
//        val responseString = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n"
//        assertEquals(responseString, rh.buildResponse(HttpMethod.UNKNOWN, "", ByteArray(0)))
//    }
//
//    @Test
//    fun buildUnimplementedResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.NOT_IMPLEMENTED.code} ${StatusCode.NOT_IMPLEMENTED.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n"
//        assertEquals(responseString, rh.buildResponse(HttpMethod.GET, "", ByteArray(0)))
//    }
//
//    @Test
//    fun buildInvalidHeaderPostResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n"
//        assertEquals(responseString, rh.buildResponse(HttpMethod.POST, "", ByteArray(0)))
//    }
//
//    @Test
//    fun buildInvalidBodyPostResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n"
//        assertEquals(responseString, rh.buildResponse(HttpMethod.POST, "application/json", null))
//    }
//
//    @Test
//    fun buildValidJsonPostResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n" +
//                "ABC".toByteArray(Charsets.UTF_8).toString(Charsets.UTF_8)
//        assertEquals(responseString, rh.buildResponse(HttpMethod.POST, "application/json", "ABC".toByteArray()) +
//                rh.response.getResponseBody()!!.toString(Charsets.UTF_8))
//    }
//
////    @Test
////    fun calculateMd5HashHexValidInput() {
////        val rh = ResponseHandler(System.out)
////        val path = Paths.get("/Users/vincente.campisi/IdeaProjects/webserver/src/test/kotlin/response/3765")
////        assertEquals("0c2da7ba464827e2fb4fbcb6ef36c6ba", rh.calculateHash(Files.readAllBytes(path)))
////    }
//
//}