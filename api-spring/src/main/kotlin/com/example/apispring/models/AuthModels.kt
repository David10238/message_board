package com.example.apispring.models

import com.example.apispring.client.makeRawSpringResponse

class Id(val id: Int?, val code: Int) {
    fun asResponse() = makeRawSpringResponse(code, emptyList(), "")
}
