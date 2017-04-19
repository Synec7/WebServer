package response

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class BadRequestResponseTest {
    @Test
    fun blankClass() {
        var response = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n"
        response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"

        assertEquals(response, BadRequestResponse().getResponseText())
    }


}