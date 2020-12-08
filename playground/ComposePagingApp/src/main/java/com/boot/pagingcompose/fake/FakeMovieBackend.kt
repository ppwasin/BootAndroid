package com.boot.pagingcompose.fake

import com.boot.pagingcompose.Movie
import com.boot.pagingcompose.MovieBackend
import com.boot.pagingcompose.MovieListResponse

private val totalPage = 3
val perPage = 10
private val totalItems = (totalPage * perPage) - (perPage / 2) //25

class FakeMovieBackend : MovieBackend {
    private val fakeMovies = generateMovies(totalItems)

    override fun getPopularMovies(page: Int): MovieListResponse {
        val movies = when (page) {
            1 -> fakeMovies.subList(0, perPage)//0,10
            2 -> fakeMovies.subList(perPage * 1, perPage * 2) //10, 20
            3 -> fakeMovies.subList(perPage * 2, perPage * 3)//20, 30
            else -> emptyList()
        }

        return MovieListResponse(
            totalPages = totalPage,
            totalResults = totalItems,
            page = page,
            results = movies
        )

    }
}

fun generateMovies(totalItems: Int): List<Movie> =
    (0..totalItems).map { Movie(id = it, title = "Movie #$it", imageUrlForSize(256, it)) }

fun imageUrlForSize(size: Int, id: Int): String = "https://i.pravatar.cc/$size?img=$id"