package com.boot.pagingcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import com.boot.pagingcompose.ui.BootAndroidTheme

class PagingComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Home()
}