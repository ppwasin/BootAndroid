package com.boot.paging3.data.rick_morty

import com.squareup.moshi.Json

enum class Gender(val gender: String) {
    @Json(name = "female")
    FEMALE("Female"),

    @Json(name ="male")
    MALE("Male"),

    @Json(name = "genderless")
    GENDERLESS("Genderless"),

    @Json(name = "unknown")
    UNKNOWN("Unknown");

    override fun toString() = gender
}