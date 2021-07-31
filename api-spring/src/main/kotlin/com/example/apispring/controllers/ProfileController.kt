package com.example.apispring.controllers

import com.example.apispring.client.AuthMicroservice
import com.example.apispring.client.ProfileMicroservice
import com.example.apispring.client.makeRawSpringResponse
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProfileController {
    @RequestMapping(value = ["/profile/merge"], method = [RequestMethod.PATCH])
    fun mergeChanges(
        @RequestHeader username: String,
        @RequestHeader token: String,
        @RequestBody changes: String
    ): ResponseEntity<String> {
        val id = AuthMicroservice.idByToken(username, token)
        if (id.id != null)
            ProfileMicroservice.mergeChanges(id.id, changes)

        return id.asResponse()
    }

    @RequestMapping(value = ["/profile/settings"], method = [RequestMethod.GET])
    fun getProfile(@RequestHeader username: String, @RequestHeader token: String): ResponseEntity<String> {
        val id = AuthMicroservice.idByToken(username, token)
        if (id.id != null) {
            return makeRawSpringResponse(ProfileMicroservice.getProfile(id.id))
        }

        return id.asResponse()
    }

    @RequestMapping(value = ["/profile/meta"], method = [RequestMethod.GET])
    fun getShortProfile(@RequestHeader id: Int) =
        makeRawSpringResponse(ProfileMicroservice.getShortProfile(id))
}
