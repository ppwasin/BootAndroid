package com.boot.entrypoint.sample.google

import androidx.compose.animation.*
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EnterExitTransitionDemo() {
	Column(Modifier.fillMaxWidth().padding(top = 20.dp)) {
		val oppositeAlignment = remember { mutableStateOf(true) }

		var alignment by remember { mutableStateOf(TopStart) }
		var visible by remember { mutableStateOf(true) }
		val (selectedOption, onOptionSelected) = remember { mutableStateOf(0) }
		Column(Modifier.fillMaxSize()) {
			Button(modifier = Modifier.gravity(CenterHorizontally), onClick = {
				alignment = TopCenter
				visible = !visible
			}) {
				Text("Top")
			}
			Row(Modifier.fillMaxWidth().weight(1f)) {
				Stack(Modifier.fillMaxHeight().wrapContentWidth()) {
					Button(modifier = Modifier.gravity(TopEnd), onClick = {
						alignment = TopStart
						visible = !visible
					}) {
						Text("Top\nStart")
					}
					Button(modifier = Modifier.gravity(CenterEnd), onClick = {
						alignment = CenterStart
						visible = !visible
					}) {
						Text("Start")
					}
					Button(modifier = Modifier.gravity(BottomEnd), onClick = {
						alignment = BottomStart
						visible = !visible
					}) {
						Text("Bottom\nStart")
					}
				}
				CenterMenu(selectedOption, oppositeAlignment.value, alignment, visible)
				Stack(Modifier.fillMaxHeight().wrapContentWidth()) {
					Button(modifier = Modifier.gravity(TopStart), onClick = {
						alignment = TopEnd
						visible = !visible
					}) {
						Text("Top\nEnd")
					}
					Button(modifier = Modifier.gravity(CenterStart), onClick = {
						alignment = CenterEnd
						visible = !visible
					}) {
						Text("End")
					}
					Button(modifier = Modifier.gravity(BottomEnd), onClick = {
						alignment = BottomEnd
						visible = !visible
					}) {
						Text("Bottom\nEnd")
					}
				}
			}
			Button(modifier = Modifier.gravity(CenterHorizontally), onClick = {
				alignment = BottomCenter
				visible = !visible
			}) {
				Text("Bottom")
			}

			alignmentOption(oppositeAlignment)
			FadeOptions(selectedOption, onOptionSelected)
		}
	}
}

@Composable
fun alignmentOption(state: MutableState<Boolean>) {
	Row(
		Modifier.selectable(selected = state.value, onClick = { state.value = !state.value })
			.padding(10.dp)
	) {
		Checkbox(state.value, { state.value = it })
		Text("Animate opposite to container alignment")
	}
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CenterMenu(
	selectedOption: Int,
	oppositeDirection: Boolean,
	alignment: Alignment,
	visible: Boolean
) {
	Stack(Modifier.fillMaxHeight().weight(1f)) {

		val animationAlignment = if (oppositeDirection) opposite(alignment) else alignment
		val enter = when (animationAlignment) {
			TopCenter -> expandVertically(expandFrom = Top)
			BottomCenter -> expandVertically(expandFrom = Bottom)
			CenterStart -> expandHorizontally(expandFrom = Start)
			CenterEnd -> expandHorizontally(expandFrom = End)
			else -> expandIn(animationAlignment)
		}.run {
			if (selectedOption >= 1) {
				this + fadeIn()
			} else {
				this
			}
		}

		val exit = when (animationAlignment) {
			TopCenter -> shrinkVertically(shrinkTowards = Top)
			BottomCenter -> shrinkVertically(shrinkTowards = Bottom)
			CenterStart -> shrinkHorizontally(shrinkTowards = Start)
			CenterEnd -> shrinkHorizontally(shrinkTowards = End)
			else -> shrinkOut(animationAlignment)
		}.run {
			if (selectedOption >= 2) {
				this + fadeOut()
			} else {
				this
			}
		}
		AnimatedVisibility(
			visible,
			Modifier.gravity(alignment),
			enter = enter,
			exit = exit
		) {
			val menuText = remember {
				mutableListOf<String>().apply {
					for (i in 0..15) {
						add("Menu Item $i")
					}
				}
			}
			LazyColumnFor(
				menuText,
				modifier = Modifier.fillMaxSize().background(Color(0xFFFFe7e7))
			) {
				Text(it, Modifier.padding(5.dp))
			}
		}
	}
}

@Composable
fun FadeOptions(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
	Column {
		Text(
			text = "Combine with:",
			modifier = Modifier.padding(start = 16.dp)
		)
		val radioOptions =
			listOf("No Fade", "Fade In", "Fade Out", "Fade In & Fade out")
		radioOptions.forEachIndexed { i, text ->
			Row(Modifier
				.fillMaxWidth()
				.preferredHeight(30.dp)
				.selectable(
					selected = (i == selectedOption),
					onClick = { onOptionSelected(i) }
				)
				.padding(horizontal = 16.dp),
				verticalGravity = Alignment.CenterVertically
			) {
				RadioButton(
					selected = (i == selectedOption),
					onClick = { onOptionSelected(i) }
				)
				Text(
					text = text,
					modifier = Modifier.padding(start = 16.dp)
				)
			}
		}
	}
}

fun opposite(alignment: Alignment): Alignment =
	when (alignment) {
		TopStart -> BottomEnd
		CenterStart -> CenterEnd
		BottomStart -> TopEnd
		TopEnd -> BottomStart
		CenterEnd -> CenterStart
		BottomEnd -> TopStart
		TopCenter -> BottomCenter
		BottomCenter -> TopCenter
		else -> alignment
	}