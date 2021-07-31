package com.example.apispring.client

object ProfileMicroservice : Microservice(5003) {
    private object Endpoints {
        const val ADD = "/add"
        const val DELETE = "/delete"
        const val MERGE = "/merge"
        const val SHORT_PROFILE = "/short"
        const val GET_PROFILE = "/profile"
    }

    fun addUser(id: Int) = requestRaw(
        HttpMethod.POST,
        Endpoints.ADD,
        listOf(idHeader(id)),
        EMPTY_BODY
    )

    fun deleteUser(id: Int) = requestRaw(
        HttpMethod.DELETE,
        Endpoints.DELETE,
        listOf(idHeader(id)),
        EMPTY_BODY
    )

    fun mergeChanges(id: Int, changes: String) = requestRaw(
        HttpMethod.PATCH,
        Endpoints.MERGE,
        listOf(idHeader(id)),
        changes
    )

    fun getShortProfile(id: Int) = requestRaw(
        HttpMethod.GET,
        Endpoints.SHORT_PROFILE,
        listOf(idHeader(id)),
        EMPTY_BODY
    )

    fun getProfile(id: Int) = requestRaw(
        HttpMethod.GET,
        Endpoints.GET_PROFILE,
        listOf(idHeader(id)),
        EMPTY_BODY
    )
}
