package com.boot.entrypoint.sample.google

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boot.entrypoint.sample.AnimateLayout
import com.boot.entrypoint.sample.AnimateTransformer
import com.boot.entrypoint.sample.google.AnimationsDemoRouting.*

enum class AnimationsDemoRouting {
	Home,
	EnterExitTransitionDemo,
	AnimatedVisibilityDemo,
	AnimatedVisibilityLazyColumnDemo,
	CrossfadeDemo,
	AnimateLayout,
	AnimateTransformer
}

@Composable
fun AnimationsDemo() {
	val (route, setRoute) = remember { mutableStateOf(Home) }
	when (route) {
		Home -> ScrollableColumn {
			for (item in AnimationsDemoRouting.values()) {
				AnimationHomeItem(item.name) { setRoute(item) }
			}
		}
		EnterExitTransitionDemo -> EnterExitTransitionDemo()
		AnimatedVisibilityDemo -> AnimatedVisibilityDemo()
		AnimatedVisibilityLazyColumnDemo -> AnimatedVisibilityLazyColumnDemo()
		CrossfadeDemo -> CrossfadeDemo()
		AnimateLayout -> AnimateLayout()
		AnimateTransformer -> AnimateTransformer()
	}
}

@Composable
fun AnimationHomeItem(title: String, onClick: () -> Unit) {
	Card(
		elevation = 4.dp,
		shape = RoundedCornerShape(4.dp),
		modifier = Modifier.padding(8.dp).clickable(onClick = onClick)
	) {
		Text(text = title, modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth())
	}
}