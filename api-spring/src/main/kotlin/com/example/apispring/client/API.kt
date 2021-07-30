package com.example.apispring.client

abstract class Microservice(port: Int) {
    private val BASE_URL = "http://localhost:${port}"
    protected fun endpoint(endpoint: String) = BASE_URL + endpoint
}
