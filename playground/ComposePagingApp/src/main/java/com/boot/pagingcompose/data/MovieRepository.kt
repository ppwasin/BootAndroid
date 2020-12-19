package com.boot.pagingcompose.data

class MovieRepository {
    private val backend: MovieBackend = FakeMovieBackend()
    fun getPopularMovies(page: Int): MovieListResponse {
        return backend.getPopularMovies(page)
    }
}