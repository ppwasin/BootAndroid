package com.boot.entrypoint.sample

import androidx.compose.animation.animate
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnimateLayout() {
	val enabled = remember { mutableStateOf(true) }
	Stack(
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
fun AnimateColor() {
	val enabled = remember { mutableStateOf(true) }
	Stack {
		Box(
			modifier = Modifier.fillMaxSize()
				.clickable(onClick = { enabled.value = !enabled.value }),
			backgroundColor = animate(if (enabled.value) Color.Green else Color.Red)
		)
		Text(text = "Click me to change color!")
	}
}

