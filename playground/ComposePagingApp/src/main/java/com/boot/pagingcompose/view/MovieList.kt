package com.boot.pagingcompose.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.boot.pagingcompose.domain.Movie
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieList(movies: Flow<PagingData<Movie>>) {
    val lazyPagingItems: LazyPagingItems<Movie> = movies.collectAsLazyPagingItems()
    LazyColumn {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        } else {
            items(lazyPagingItems) { movie ->
                when {
                    movie != null -> MovieItem(movie = movie)
                    else -> Text(
                        modifier = Modifier.height(128.dp).fillMaxWidth(),
                        text = "Show Place holder"
                    )
                }

            }
        }
        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}