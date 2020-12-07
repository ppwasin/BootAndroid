package com.boot.pagingcompose

data class MovieListResponse(
    val totalPages: Int,
    val totalResults: Int,
    val results: List<Movie>,
    val page: Int
)