package com.boot.paging3.data.rick_morty

import com.squareup.moshi.Json

enum class Status(val status: String) {
    @Json(name = "alive")
    ALIVE("Alive"),

    @Json(name = "dead")
    DEAD("Dead"),

    @Json(name = "unknown")
    UNKNOWN("Unknown");

    override fun toString() = status
}