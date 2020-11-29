package com.boot.projectMgr.ex.textfield.phone

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.input.OffsetMap
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

object PhoneNumberTransformation : VisualTransformation {
	
	private val mutedStyle = SpanStyle(color = Color.Gray)
	
	override fun filter(text: AnnotatedString): TransformedText {
		val rawText = text.text
		val countryCode: String
		val areaCode: String
		val firstNumbers: String
		val secondNumbers: String
		
		when (rawText.length) {
			// 012 345 6789
			in 0..10 -> {
				countryCode = ""
				areaCode = rawText.substringSafe(0, 3)
				firstNumbers = rawText.substringSafe(3, 6)
				secondNumbers = rawText.substringSafe(6)
			}
			else -> {
				countryCode = ""
				areaCode = rawText.substringSafe(0, 3)
				firstNumbers = rawText.substringSafe(3, 6)
				secondNumbers = rawText.substringSafe(6)
			}
		}
		// 5554 -> (555)-4
		// +1 (5555) 444-666
		return TransformedText(
			annotatedString {
				withStyle(mutedStyle) { append("(") }
				append(areaCode)
				if (areaCode.length < 3) {
					withStyle(mutedStyle) {
						append("#".repeat(3 - areaCode.length))
					}
				}
				withStyle(mutedStyle) { append(")") }
				
				append(firstNumbers)
				if (firstNumbers.length < 3) {
					withStyle(mutedStyle) {
						append("#".repeat(3 - firstNumbers.length))
					}
				}
				withStyle(mutedStyle) { append("-") }
				append(secondNumbers)
				if (secondNumbers.length < 4) {
					withStyle(mutedStyle) {
						append("#".repeat(4 - secondNumbers.length))
					}
				}
			},
			object : OffsetMap {
				// (012)345-6789
				// 0123456789
				override fun originalToTransformed(offset: Int): Int = when {
					offset < 3 -> offset + 1
					offset < 6 -> offset + 2
					else -> offset + 3
				}
				
				override fun transformedToOriginal(offset: Int): Int = when {
					offset <= 1 -> 0
					offset < 4 -> offset - 1
					offset < 8 -> offset - 2
					else -> offset - 3
				}
				
			}
		)
	}
	
}