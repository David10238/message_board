package com.example.apispring.models

import com.google.gson.Gson

private val GSON = Gson()

fun toJson(dat: Any) = GSON.toJson(dat)
fun <T> fromJSon(string: String, clazz: Class<T>): T = GSON.fromJson(string, clazz)
