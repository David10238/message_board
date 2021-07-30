package com.example.apispring.controllers

import com.example.apispring.client.AuthMicroservice
import com.example.apispring.client.makeRawSpringResponse
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
    fun deleteUser(@RequestHeader username: String, @RequestHeader password: String) =
        makeRawSpringResponse(AuthMicroservice.deleteUser(username, password))

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
        synchronized(newUserLock) {
            return makeRawSpringResponse(AuthMicroservice.addUser(username, password))
        }
    }
}
