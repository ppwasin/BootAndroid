package com.boot.projectMgr.ex.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMap
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

@Composable
@ExperimentalFocus
fun Playground() {
	TypeAhead2()
}

val items = listOf(
	"foo",
	"bar",
	"baz",
	"hello this is a long string"
)

val TextFieldValue.unselectedText: String
	get() = text.removeRange(selection.start, selection.end)

class TypeAheadTransformation(items: List<String>) : VisualTransformation {
	
	override fun filter(text: AnnotatedString): TransformedText {
		val prefix = text.text
		val result = items.firstOrNull { it.startsWith(prefix) }
		return when {
			prefix.isEmpty() || result == null ->
				TransformedText(text, OffsetMap.identityOffsetMap)
			else -> TransformedText(
				annotatedString {
					append(prefix)
					withStyle(SpanStyle(color = Color.Gray)) {
						append(result.substring(prefix.length))
					}
				},
				object : OffsetMap {
					override fun originalToTransformed(offset: Int): Int {
						return offset
					}
					
					override fun transformedToOriginal(offset: Int): Int {
						return offset.coerceAtMost(prefix.length)
					}
					
				}
			)
		}
	}
	
}

@Composable
fun TypeAhead2() {
	var value by remember { mutableStateOf("") }
	
	TextField(
		value = value,
		onValueChange = { value = it },
		visualTransformation = TypeAheadTransformation(items)
	
	)
}

@Composable
fun TypeAhead() {
	var value by remember { mutableStateOf(TextFieldValue("")) }
	
	TextField(
		value = value,
		onValueChange = { next ->
			val prefix = next.text
			val result = items.firstOrNull { it.startsWith(prefix) }
			value = when {
				value.text == prefix -> next
				prefix.isEmpty() -> next
				result == null -> next
				//user hit backspace
				value.unselectedText == next.text && next.selection.length == 0 ->
					next.copy(
						text = result,
						selection = TextRange(prefix.length - 1, result.length),
						composition = null
					)
				else -> next.copy(
					text = result,
					selection = TextRange(prefix.length, result.length),
					composition = null
				)
			}
		})
}

@ExperimentalFocus
@Composable
fun BasicForm() {
	var value1 by remember { mutableStateOf("") }
	val focus1 = remember { FocusRequester() }
	var value2 by remember { mutableStateOf("") }
	val focus2 = remember { FocusRequester() }
	var value3 by remember { mutableStateOf("") }
	val focus3 = remember { FocusRequester() }
	var value4 by remember { mutableStateOf("") }
	val focus4 = remember { FocusRequester() }
	
	Column {
		Button(onClick = {
			focus3.requestFocus()
		}) {
			Text("Enter Value 3")
		}
		
		TextField(
			modifier = Modifier.focusRequester(focus1),
			label = { Text("Value1") },
			value = value1,
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
			onImeActionPerformed = { action, controller ->
				focus2.requestFocus()
			},
			onValueChange = { value1 = it }
		)
		TextField(
			modifier = Modifier.focusRequester(focus2),
			label = { Text("Value2") },
			value = value2,
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
			onImeActionPerformed = { action, controller ->
				focus3.requestFocus()
			},
			onValueChange = { value2 = it }
		)
		TextField(
			modifier = Modifier.focusRequester(focus3),
			label = { Text("Value3") },
			value = value3,
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
			onImeActionPerformed = { action, controller ->
				focus4.requestFocus()
			},
			onValueChange = { value3 = it }
		)
		TextField(
			modifier = Modifier.focusRequester(focus4),
			label = { Text("Value4") },
			value = value4,
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
			onImeActionPerformed = { action, controller ->
				controller?.hideSoftwareKeyboard()
			},
			onValueChange = { value4 = it }
		)
	}
	
}

