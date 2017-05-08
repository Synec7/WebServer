package app.http.request

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class MethodValidator {
    fun validateMethod(input: String): app.http.HttpMethod =
            try {
                app.http.HttpMethod.valueOf(input.split(" ").toTypedArray()[0])
            } catch(e: Exception) {
                app.http.HttpMethod.UNKNOWN
                throw IllegalArgumentException("Invalid HTTP app.http.request.")
            } catch (ae: AssertionError) {
                app.http.HttpMethod.UNKNOWN
                throw IllegalArgumentException("Invalid HTTP app.http.request.")
            }
}