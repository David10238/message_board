package com.example.apispring.controllers

import com.example.apispring.client.AuthMicroservice
import com.example.apispring.client.ProfileMicroservice
import com.example.apispring.client.RawResponse
import com.example.apispring.client.makeRawSpringResponse
import com.example.apispring.models.toJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    companion object {
        @JvmStatic
        private val newUserLock = Object()
    }

    @RequestMapping(value = ["/auth/delete"], method = [RequestMethod.DELETE])
    fun deleteUser(@RequestHeader username: String, @RequestHeader password: String): ResponseEntity<String> {
        val deleteUserRes = AuthMicroservice.deleteUser(username, password)
        val id = AuthMicroservice.parseID(deleteUserRes)
        if (id.id != null)
            ProfileMicroservice.deleteUser(id.id)
        return makeRawSpringResponse(
            id.code,
            emptyList(),
            ""
        )
    }

    @RequestMapping(value = ["/auth/changePassword"], method = [RequestMethod.PATCH])
    fun changePassword(
        @RequestHeader username: String,
        @RequestHeader password: String,
        @RequestHeader newPassword: String
    ) = makeRawSpringResponse(AuthMicroservice.changePassword(username, password, newPassword))

    @RequestMapping(value = ["/auth/refreshToken"], method = [RequestMethod.PATCH])
    fun refreshToken(@RequestHeader username: String, @RequestHeader token: String) =
        makeRawSpringResponse(AuthMicroservice.refreshToken(username, token))

    @RequestMapping(value = ["/auth/doesNameExist"], method = [RequestMethod.GET])
    fun doesNameExist(@RequestHeader username: String) =
        makeRawSpringResponse(AuthMicroservice.doesNameExist(username))

    @RequestMapping(value = ["/auth/token"], method = [RequestMethod.GET])
    fun getToken(@RequestHeader username: String, @RequestHeader password: String) =
        makeRawSpringResponse(AuthMicroservice.getToken(username, password))

    @RequestMapping(value = ["/auth/create"], method = [RequestMethod.POST])
    fun createNewUser(@RequestHeader username: String, @RequestHeader password: String): ResponseEntity<String> {
        val createUserRes: RawResponse = AuthMicroservice.addUser(username, password)
        val id = AuthMicroservice.parseID(createUserRes)

        val userCreated = id.id != null

        if (userCreated)
            ProfileMicroservice.addUser(id.id!!)

        return makeRawSpringResponse(
            id.code,
            emptyList(),
            toJson(userCreated)
        )
    }
}
