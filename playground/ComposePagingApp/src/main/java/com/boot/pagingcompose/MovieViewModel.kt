package com.boot.pagingcompose

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.boot.pagingcompose.fake.perPage
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {
    private val movieRepository: MovieRepository = MovieRepository()
    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = perPage)) {
        MovieSource(movieRepository)
    }.flow
}