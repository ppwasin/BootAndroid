package com.boot.pagingcompose.data

interface MovieBackend {
    fun getPopularMovies(page: Int): MovieListResponse
}