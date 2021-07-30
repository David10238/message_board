package com.example.apispring.client

fun usernameHeader(username: String) = Header("username", username)
fun tokenHeader(token: String) = Header("token", token)
