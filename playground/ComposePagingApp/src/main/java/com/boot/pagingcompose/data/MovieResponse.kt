package com.boot.pagingcompose.data

import com.boot.pagingcompose.domain.Movie

data class MovieListResponse(
    val totalPages: Int,
    val totalResults: Int,
    val results: List<Movie>,
    val page: Int
)