package com.boot.paging3.data.rick_morty

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<Character>>
}