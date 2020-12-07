package com.boot.pagingcompose

interface MovieBackend {
    fun getPopularMovies(page: Int): MovieListResponse
}