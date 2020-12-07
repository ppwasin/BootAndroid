package com.boot.pagingcompose

import androidx.paging.PagingSource
import timber.log.Timber

class MovieSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val movieListResponse = movieRepository.getPopularMovies(page)
            Timber.v("request page: ${params.loadSize}, page $page => result: $movieListResponse")
            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            Timber.e("load page error: $e")
            LoadResult.Error(e)
        }
    }
}