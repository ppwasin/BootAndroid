package com.boot.entrypoint.anim

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@ExperimentalAnimationApi
@Composable
fun SlideTransition(visible: Boolean, children: @Composable () -> Unit) {
	AnimatedVisibility(
		visible = visible,
		enter = slideInHorizontally(
			// Offsets the content by 1/3 of its width to the left, and slide towards right
			initialOffsetX = { fullWidth -> -fullWidth / 3 },
			// Overwrites the default animation with tween for this slide animation.
			animSpec = tween(durationMillis = 200)
		) + fadeIn(
			// Overwrites the default animation with tween
			animSpec = tween(durationMillis = 200)
		),
		exit = slideOutHorizontally(
			// Overwrites the ending position of the slide-out to 200 (pixels) to the right
			targetOffsetX = { 200 },
			animSpec = spring(stiffness = Spring.StiffnessHigh)
		) + fadeOut()
	) {
		children()
	}
}