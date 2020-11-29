package com.boot.projectMgr.ex.textfield.phone

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Number
import androidx.compose.ui.unit.dp

// visual transformation phone number
@Composable
fun PhoneNumberFiled() {
	var phoneNumber by remember { mutableStateOf("") }
	TextField(
		modifier = Modifier.padding(16.dp),
		value = phoneNumber,
		onValueChange = {
			phoneNumber = it.replace(Regex("\\D"), "")
		},
		keyboardOptions = KeyboardOptions(keyboardType = Number),
		visualTransformation = PhoneNumberTransformation
	)
}