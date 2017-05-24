package http


import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HeaderReaderTest {

    @Test
    fun listOneHeader() {
        val hr = listOf(HttpHeader("notHere", "notThere"))
        assertEquals("notHere:notThere\r\n", hr.listHeaders())
    }

    @Test
    fun listTwoHeaders() {
        val hr = listOf(
                HttpHeader("notHere", "notThere"),
                HttpHeader("norHere", "norThere"))
        assertEquals("notHere:notThere\r\nnorHere:norThere\r\n", hr.listHeaders())
    }

    @Test
    fun listThreeHeaders() {
        val hr = listOf(
                HttpHeader("notHere", "notThere"),
                HttpHeader("norHere", "norThere"),
                HttpHeader("norAnywhere", "norAnywhere"))
        assertEquals("notHere:notThere\r\nnorHere:norThere\r\nnorAnywhere:norAnywhere\r\n", hr.listHeaders())
    }

    @Test
    fun listBlankHeaders() {
        val hr = (listOf(HttpHeader("", "")))
        assertEquals(":\r\n", hr.listHeaders())
    }

    @Test
    fun getHeaderValueByName() {
        val hr = listOf(
                HttpHeader("notHere", "notThere"),
                HttpHeader("norHere", "norThere"),
                HttpHeader("norAnywhere", "norAnywhere"))
        assertEquals("norThere", hr.getHeader("norHere"))
    }

    @Test
    fun getHeaderValueByNameCapitalized() {
        val hr = listOf(
                HttpHeader("notHere", "notThere"),
                HttpHeader("norHere", "norThere"),
                HttpHeader("norAnywhere", "norAnywhere"))
        assertEquals("notThere", hr.getHeader("NOTHERE"))
    }

    @Test(expected = NoSuchElementException::class)
    fun getHeaderValueException() {
        val hr = listOf(
                HttpHeader("notHere", "notThere"),
                HttpHeader("norHere", "norThere"),
                HttpHeader("norAnywhere", "norAnywhere"))
        hr.getHeader("nonExistent")
    }
}