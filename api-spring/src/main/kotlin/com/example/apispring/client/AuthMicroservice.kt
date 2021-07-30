package com.example.apispring.client

object AuthMicroservice : Microservice(5001) {
    fun refreshToken(username: String, token: String): RawResponse {
        return requestRaw(
            HttpMethod.PATCH,
            "/refreshToken",
            listOf(usernameHeader(username), tokenHeader(token)),
            "this is a test body"
        )
    }
}
