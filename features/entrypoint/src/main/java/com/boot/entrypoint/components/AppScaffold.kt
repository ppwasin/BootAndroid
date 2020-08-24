package com.boot.entrypoint.components

import androidx.compose.animation.*
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AppScaffold(
	title: String,
	backNavigation: (() -> Unit)? = null,
	content: @Composable (() -> Unit)
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					AnimatedVisibility(
						initiallyVisible = false,
						visible = true,
						enter = fadeIn() + expandHorizontally(),
						exit = fadeOut() + shrinkHorizontally()
					) { Text(text = title) }
				},
				navigationIcon = if (backNavigation != null) {
					{ AppBackButton(backNavigation) }
				} else null
			)
		}
	) {
		content()
	}
}

@Composable
fun AppBackButton(backNavigation: () -> Unit) {
	IconButton(onClick = backNavigation) {
		Icon(Icons.Filled.ArrowBack)
	}
}