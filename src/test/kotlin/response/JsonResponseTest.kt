package response

import org.junit.Assert
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 19/04/17.
 */
class JsonResponseTest {

    @Test(expected = IllegalArgumentException::class)
    fun nullBody() {
        var br = BinaryResponse(ByteArray(0))
    }

    @Test
    fun abc() {
        var br = JsonResponse("abc".toByteArray(Charsets.ISO_8859_1))
        Assert.assertNotNull(br)
        Assert.assertNotNull(br.headers)
        Assert.assertNotNull(br.responseBody)
        Assert.assertNotNull(br.responseText)
        Assert.assertEquals(br.httpVersion, "HTTP/1.1")

        var response = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n"
        response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"
        response += "Content-Length:3\r\n"
        response += "Content-Type:application/json;charset=utf-8\r\n"

        Assert.assertEquals(response, br.getResponseText())
        Assert.assertEquals("abc", br.responseBody.toString(Charsets.UTF_8))
    }
}