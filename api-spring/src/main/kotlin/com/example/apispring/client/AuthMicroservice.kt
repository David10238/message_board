package com.example.apispring.client

object AuthMicroservice : Microservice(5001) {
    private object Endpoints {
        const val DELETE_USER = "/deleteUser"
        const val CHANGE_PASSWORD = "/changePassword"
        const val REFRESH_TOKEN = "/refreshToken"
        const val DOES_NAME_EXIST = "/doesNameExist"
        const val GET_TOKEN = "/getToken"
        const val ADD_USER = "/addUser"
    }

    fun deleteUser(username: String, password: String) = requestRaw(
        HttpMethod.DELETE,
        Endpoints.DELETE_USER,
        listOf(usernameHeader(username), passwordHeader(password)),
        EMPTY_BODY
    )

    fun changePassword(username: String, password: String, newPassword: String) = requestRaw(
        HttpMethod.PATCH,
        Endpoints.CHANGE_PASSWORD,
        listOf(usernameHeader(username), passwordHeader(password), newPasswordHeader(newPassword)),
        EMPTY_BODY
    )

    fun refreshToken(username: String, token: String): RawResponse = requestRaw(
        HttpMethod.PATCH,
        Endpoints.REFRESH_TOKEN,
        listOf(usernameHeader(username), tokenHeader(token)),
        EMPTY_BODY
    )

    fun doesNameExist(username: String) = requestRaw(
        HttpMethod.GET,
        Endpoints.DOES_NAME_EXIST,
        listOf(usernameHeader(username)),
        EMPTY_BODY
    )

    fun getToken(username: String, password: String) = requestRaw(
        HttpMethod.GET,
        Endpoints.GET_TOKEN,
        listOf(usernameHeader(username), passwordHeader(password)),
        EMPTY_BODY
    )

    fun addUser(username: String, password: String) = requestRaw(
        HttpMethod.POST,
        Endpoints.ADD_USER,
        listOf(usernameHeader(username), passwordHeader(password)),
        EMPTY_BODY
    )
}

