package core.protocol

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
interface ResponseBuilder {
	fun buildResponse(request: Request): Response
}