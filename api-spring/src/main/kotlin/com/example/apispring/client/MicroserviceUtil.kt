package com.example.apispring.client

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Header(val key: String, val values: List<String>) {
    constructor(key: String, value: String) : this(key, listOf(value))
}

enum class HttpMethod {
    GET,
    PUT,
    POST,
    DELETE,
    PATCH,
}

class RawResponse internal constructor(response: HttpResponse<String>) {
    val code = response.statusCode()
    val isError = code != HttpStatus.ACCEPTED.value()
    val headers = response.headers().map().map {
        Header(it.key, it.value)
    }

    val body = response.body()
}

fun makeRawSpringResponse(rawResponse: RawResponse) =
    makeRawSpringResponse(rawResponse.code, rawResponse.headers, rawResponse.body)

fun makeRawSpringResponse(code: Int, headers: List<Header>, body: String): ResponseEntity<String> {
    val springHeaders = HttpHeaders()
    for (header in headers)
        springHeaders[header.key] = header.values
    return ResponseEntity.status(code).headers(springHeaders).body(body)
}

abstract class Microservice(private val PORT: Int) {
    protected fun requestRaw(method: HttpMethod, endpoint: String, headers: List<Header>, body: String): RawResponse {
        val httpClient = HttpClient.newBuilder()
            .build()

        val requestBuilder = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:$PORT$endpoint"))
            .method(method.toString(), HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")

        for (header in headers)
            for (value in header.values)
                requestBuilder.header(header.key, value)

        return RawResponse(httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString()))
    }
}
