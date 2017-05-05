package request

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import http.HttpMethod
import http.HttpRequestReader
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.InputStream

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class HttpRequestReaderTest {
    @Test(expected = IllegalArgumentException::class)
    fun readNull() {
        val mockInputStream = mock<InputStream> {
            on { read() } doReturn -1
        }

        val classUnderTest = HttpRequestReader(mockInputStream)
        assertEquals(HttpMethod.UNKNOWN, classUnderTest.readMethod())
    }

    @Test(expected = IllegalArgumentException::class)
    fun readSpace() {
        val mockInputStream = mock<InputStream> {
            on { read() } doReturn ' '.toInt() doReturn -1
        }

        val classUnderTest = HttpRequestReader(mockInputStream)
        assertEquals(HttpMethod.UNKNOWN, classUnderTest.readMethod())
    }

    @Test
    fun readPOST() {
        val mockInputStream = mock<InputStream> {
            on { read() } doReturn 'P'.toInt() doReturn 'O'.toInt() doReturn
                    'S'.toInt() doReturn 'T'.toInt() doReturn -1
        }

        val classUnderTest = HttpRequestReader(mockInputStream)
        assertEquals(HttpMethod.POST, classUnderTest.readMethod())
    }

    @Test(expected = IllegalArgumentException::class)
    fun readSpaceWithPOST() {
        val mockInputStream = mock<InputStream> {
            on { read() } doReturn ' '.toInt() doReturn 'P'.toInt() doReturn 'O'.toInt() doReturn
                    'S'.toInt() doReturn 'T'.toInt() doReturn -1
        }

        val classUnderTest = HttpRequestReader(mockInputStream)
        assertEquals(HttpMethod.UNKNOWN, classUnderTest.readMethod())
    }

    @Test
    fun readBytes() {
        val classUnderTest = HttpRequestReader(ByteArrayInputStream("Th\n".toByteArray()))
        var byteArray = ByteArray(3)

        assertEquals(3, classUnderTest.readBytes(3, byteArray))
    }

    @Test
    fun readBytesInvalid() {
        val classUnderTest = HttpRequestReader(ByteArrayInputStream("".toByteArray()))
        var byteArray = ByteArray(1)

        assertEquals(-1, classUnderTest.readBytes(1, byteArray))
    }

    @Test
    fun readHeaders() {

    }


//        @Test
//    fun readNullLine() {
//        val mockInputStream = mock<InputStream> {
//            on { read() } doReturn -1
//        }
//
//        val classUnderTest = HttpRequestReader(mockInputStream)
//        assertEquals("", classUnderTest.readLine())
//    }
//
//    @Test
//    fun readSpaceLine() {
//        val mockInputStream = mock<InputStream> {
//            on { read() } doReturn ' '.toInt() doReturn -1
//        }
//
//        val classUnderTest = HttpRequestReader(mockInputStream)
//        assertEquals(" ", classUnderTest.readLine())
//    }
//
//    @Test
//    fun readFullLine() {
//        val mockInputStream = mock<InputStream> {
//            val string = "This is a test line\n".toCharArray()
//            val iterator = string.iterator()
//
//            on { read() } doReturn 'T'.toInt() doReturn 'h'.toInt() doReturn '\n'.toInt() doReturn -1
//        }
//
//        val classUnderTest = HttpRequestReader(mockInputStream)
//        assertEquals("Th\n", classUnderTest.readLine())
//    }
}