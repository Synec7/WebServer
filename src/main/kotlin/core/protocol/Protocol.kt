package core.protocol

/**
* Created by Vincente A. Campisi on 24/05/17.
*/
interface Protocol {
	fun getRequestHandler(): RequestHandler
	fun getResponseBuilder(): ResponseBuilder
	fun getResponseDispatcher(): ResponseDispatcher
}