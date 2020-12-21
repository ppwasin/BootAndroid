package com.boot.parallaxapp

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Parallax(modifier: Modifier = Modifier) {


    val scrollableState = rememberLazyListState()
    val offsetMap = remember { mutableMapOf<Int, Int>() }
    offsetMap[scrollableState.firstVisibleItemIndex] = scrollableState.firstVisibleItemScrollOffset
    val offset = offsetMap.values
        .reduce { acc, i -> acc + i }
        .toFloat()

    LazyRow(
        modifier = modifier,
        state = scrollableState
    ) {
        items(items) {
            ItemCard(item = it, scrollX = offset)
        }
    }

//    val scrollState1 = rememberScrollState()
//        ScrollableRow(
//            modifier = modifier,
//            scrollState = scrollState1
//        ) {
//            items.forEach {
//                ItemCard(item = it, scrollX = scrollState1.value)
//            }
//        }
}