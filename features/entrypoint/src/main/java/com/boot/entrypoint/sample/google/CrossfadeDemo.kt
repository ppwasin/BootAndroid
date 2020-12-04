package com.boot.entrypoint.sample.google


import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.random.Random

@Composable
fun CrossfadeDemo() {
	var current by remember { mutableStateOf(tabs[0]) }
	Column {
		Row {
			tabs.forEach { tab ->
				Box(
                    Modifier.tapGestureFilter(onTap = {
                        Log.e("Crossfade", "Switch to $tab")
                        current = tab
                    })
                        .weight(1f, true)
                        .preferredHeight(48.dp)
                        .background(color = tab.color)

                )
			}
		}
		Crossfade(current = current) { tab ->
            tab.lastInt = remember { Random.nextInt() }
            Box(Modifier.fillMaxSize().background(color = tab.color))
		}
	}
}

private val tabs = listOf(
	Tab(Color(1f, 0f, 0f)),
	Tab(Color(0f, 1f, 0f)),
	Tab(Color(0f, 0f, 1f))
)

private data class Tab(val color: Color) {
	var lastInt: Int = 0
		set(value) {
			if (value != field) {
				Timber.e("State recreated for $color")
				field = value
			}
		}
}