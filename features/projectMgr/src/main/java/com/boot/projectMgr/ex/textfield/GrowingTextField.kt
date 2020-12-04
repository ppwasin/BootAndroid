package com.boot.projectMgr.ex.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// grow  the textfield as the value grows
@Composable
fun GrowingTextField() {
	var message by remember { mutableStateOf("") }
	var expended by remember { mutableStateOf(true) }
	
	Column {
		Checkbox(
			checked = expended,
			onCheckedChange = { expended = it })
		
		TextField(
			modifier = Modifier
				.padding(16.dp)
				.height(45.dp),
			value = message,
			onValueChange = { message = it },
			maxLines = if (expended) Int.MAX_VALUE else 1
		)
	}
}