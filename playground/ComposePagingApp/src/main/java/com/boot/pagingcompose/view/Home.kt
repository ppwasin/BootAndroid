package com.boot.pagingcompose.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.boot.pagingcompose.ui.BootAndroidTheme

/*
- Source: https://proandroiddev.com/infinite-lists-with-paging-3-in-jetpack-compose-b095533aefe6
- KnownIssues: Kind of lack on scrolling
*/
@Composable
fun Home() {
    BootAndroidTheme {
        Surface(color = MaterialTheme.colors.background) {
            val viewModel: MovieViewModel = viewModel()
            MovieList(movies = viewModel.movies)
        }
    }
}