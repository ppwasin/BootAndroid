package com.boot.pagingcompose

import com.boot.pagingcompose.fake.FakeMovieBackend

class MovieRepository {
    private val backend: MovieBackend = FakeMovieBackend()
    fun getPopularMovies(page: Int): MovieListResponse {
        return backend.getPopularMovies(page)
    }
}