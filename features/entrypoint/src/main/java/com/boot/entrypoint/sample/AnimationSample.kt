package com.boot.entrypoint.sample

import androidx.compose.animation.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.boot.entrypoint.page.Book.Companion.mock


@Composable
fun AnimateLayout() {
    val enabled = remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.padding(
            animate(if (enabled.value) 0f else 100f).dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable(onClick = { enabled.value = !enabled.value })
        )
        Text(text = "Click me to change color!")
    }
}

@Composable
fun AnimateTransformer() {
    val enabled = remember { mutableStateOf(true) }
    val book = remember { mock.first() }


    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .padding(8.dp).fillMaxWidth()
            .fillMaxHeight(animate(if (enabled.value) 0.5f else 1f))
            .clickable(onClick = { enabled.value = !enabled.value })
    ) {
        Row {

            Column {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "book_info_year_pages",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

}

@Composable
fun AnimateColor() {
    val enabled = remember { mutableStateOf(true) }
    Box {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable(onClick = { enabled.value = !enabled.value })
                .background(color = animate(if (enabled.value) Color.Green else Color.Red)),
        )
        Text(text = "Click me to change color!")
    }
}

