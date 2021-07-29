package com.boot.paging3.data.rick_morty

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)

