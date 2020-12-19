package com.boot.pagingcompose.view

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.boot.pagingcompose.data.MovieRepository
import com.boot.pagingcompose.data.perPage
import com.boot.pagingcompose.domain.Movie
import com.boot.pagingcompose.domain.MovieSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {
    private val movieRepository: MovieRepository = MovieRepository()
    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = perPage)) {
        MovieSource(movieRepository)
    }.flow
}