package com.boot.pagingcompose.domain

import androidx.paging.PagingSource
import com.boot.pagingcompose.data.MovieRepository
import timber.log.Timber

class MovieSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = movieRepository.getPopularMovies(page)
            Timber.v("request page: ${params.loadSize}, page $page => result: $response")
            val isLastPage = response.page == response.totalPages
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (isLastPage) null else response.page.plus(1)
            )
        } catch (e: Exception) {
            Timber.e("load page error: $e")
            LoadResult.Error(e)
        }
    }
//
//    private fun isLastPage(total: Int, currentPage: Int, perPage: Int): Boolean {
//        return currentPage * perPage >= total
//    }
}