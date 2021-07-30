package com.example.apispring.client

fun usernameHeader(username: String) = Header("username", username)
fun passwordHeader(password: String) = Header("password", password)
fun newPasswordHeader(newPassword: String) = Header("newPassword", newPassword)
fun tokenHeader(token: String) = Header("token", token)
