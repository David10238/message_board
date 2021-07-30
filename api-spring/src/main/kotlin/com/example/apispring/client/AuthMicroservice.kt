package com.example.apispring.client

import com.example.apispring.models.Id
import com.example.apispring.models.fromJSon
import org.springframework.http.HttpStatus

object AuthMicroservice : Microservice(5001) {
    private object Endpoints {
        const val DELETE_USER = "/deleteUser"
        const val CHANGE_PASSWORD = "/changePassword"
        const val REFRESH_TOKEN = "/refreshToken"
        const val DOES_NAME_EXIST = "/doesNameExist"
        const val GET_TOKEN = "/getToken"
        const val ADD_USER = "/addUser"
        const val BY_PASSWORD = "/byPassword"
        const val BY_TOKEN = "/byToken"
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

    @Synchronized
    fun addUser(username: String, password: String) = requestRaw(
        HttpMethod.POST,
        Endpoints.ADD_USER,
        listOf(usernameHeader(username), passwordHeader(password)),
        EMPTY_BODY
    )

    fun parseID(res: RawResponse): Id {
        val code = res.code
        val id = if (code == HttpStatus.ACCEPTED.value()) fromJSon(res.body, Int::class.java) else null
        return Id(id, code)
    }

    fun idByToken(username: String, token: String) = parseID(
        requestRaw(
            HttpMethod.GET,
            Endpoints.BY_TOKEN,
            listOf(usernameHeader(username), tokenHeader(token)),
            EMPTY_BODY
        )
    )

    fun idByPassword(username: String, password: String) = parseID(
        requestRaw(
            HttpMethod.GET,
            Endpoints.BY_PASSWORD,
            listOf(usernameHeader(username), passwordHeader(password)),
            EMPTY_BODY
        )
    )
}

