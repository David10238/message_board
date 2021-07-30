package com.example.apispring.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @RequestMapping(value = ["/auth/delete"], method = [RequestMethod.DELETE])
    fun deleteUser(@RequestHeader username: String, @RequestHeader password: String): String {
        return "Deletion not implemented: $username, $password"
    }
}
