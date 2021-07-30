package com.example.apispring.client

import java.net.InetSocketAddress
import java.net.ProxySelector
import java.net.http.HttpClient
import java.net.http.HttpRequest

enum class HttpMethod {
    GET,
    PUT,
    POST,
    DELETE,
    PATCH,
}

private val BASE_URL = "http://localhost"

abstract class Microservice(private val PORT: Int) {
    protected fun request(method: HttpMethod, endpoint:String, headers:Pair<String, String>, body:String) {
        val httpClient = HttpClient.newBuilder()
            .proxy(ProxySelector.of(InetSocketAddress(BASE_URL, PORT)))
            .build()

        val requestBuilder = HttpRequest.newBuilder()
            .method(method.toString(), HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")
    }
}
