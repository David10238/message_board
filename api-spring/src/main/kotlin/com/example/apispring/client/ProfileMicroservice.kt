package com.example.apispring.client

object ProfileMicroservice : Microservice(5003) {
    private object Endpoints {
        const val ADD = "/add"
    }

    fun addUser(id:Int) = requestRaw(
        HttpMethod.POST,
        Endpoints.ADD,
        listOf(idHeader(id)),
        EMPTY_BODY
    )
}
