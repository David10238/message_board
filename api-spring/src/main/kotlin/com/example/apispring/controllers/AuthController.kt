package com.example.apispring.controllers

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @RequestMapping(value = ["/auth/delete"], method = [RequestMethod.DELETE])
    fun deleteUser(@RequestHeader username: String, @RequestHeader password: String) {
    }

    @RequestMapping(value = ["/auth/changePassword"], method = [RequestMethod.PATCH])
    fun changePassword(
        @RequestHeader username: String,
        @RequestHeader password: String,
        @RequestHeader newPassword: String
    ) {
    }

    @RequestMapping(value = ["/auth/refreshToken"], method = [RequestMethod.PATCH])
    fun refreshToken(@RequestHeader username: String, @RequestHeader token: String) {

    }

    @RequestMapping(value = ["/auth/doesNameExist"], method = [RequestMethod.GET])
    fun doesNameExist(@RequestHeader username: String) {

    }

    @RequestMapping(value = ["/auth/token"], method = [RequestMethod.GET])
    fun getToken(@RequestHeader username: String, @RequestHeader password: String) {

    }

    @RequestMapping(value = ["/auth/create"], method = [RequestMethod.POST])
    fun createNewUser(@RequestHeader username: String, @RequestHeader password: String) {

    }
}
