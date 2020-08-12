package com.boot.entrypoint.components

import androidx.compose.foundation.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.boot.entrypoint.MainScreen

@Composable
fun AppTopbar(screen: MainScreen) {
	TopAppBar(
		title = {
			when (screen) {
				MainScreen.Page1 -> Text(text = "Page1")
				MainScreen.Page2 -> Text(text = "Page2")
				MainScreen.Page3 -> Text(text = "Page3")
			}
		},
//		navigationIcon = {
//			IconButton(onClick = { }) {
//				Icon(Icons.Filled.ArrowBack)
//			}
//		}
	)
}