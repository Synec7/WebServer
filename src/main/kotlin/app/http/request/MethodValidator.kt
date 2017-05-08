package app.http.request

import app.http.HttpMethod

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class MethodValidator {
    fun validateMethod(input: String): HttpMethod =
            try {
                HttpMethod.valueOf(input.split(" ").toTypedArray()[0])
            } catch(e: Exception) {
                HttpMethod.UNKNOWN
                throw IllegalArgumentException("Invalid HTTP app.http.request.")
            } catch (ae: AssertionError) {
                HttpMethod.UNKNOWN
                throw IllegalArgumentException("Invalid HTTP app.http.request.")
            }
}