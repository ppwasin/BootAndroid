package com.boot.paging3.data.bridge

import android.content.Context
import com.boot.core.network.NetworkModule
import com.boot.paging3.data.rick_morty.CharacterApi

class Paging3NetworkModule {
    val context: Context
    val baseURL: String
    val networkModule: NetworkModule
    fun characterApi(): CharacterApi {
        networkModule
    }
}