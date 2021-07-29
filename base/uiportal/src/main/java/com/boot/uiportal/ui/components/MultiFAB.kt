package com.boot.uiportal.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MultiFloatingActionButton(fabIcon: ImageBitmap,
                              toState: MultiFabState) {

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    MultiFloatingActionButton(toState = toState)
}