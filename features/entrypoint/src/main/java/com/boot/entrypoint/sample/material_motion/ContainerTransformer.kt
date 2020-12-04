package com.boot.entrypoint.sample.material_motion

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxBy
import com.boot.entrypoint.R
import com.boot.entrypoint.page.Book
import timber.log.Timber


@Composable
fun ContainerTransformerDemo() {
	val (isSmall, setIsSmall) = remember { mutableStateOf(true) }
	val book = remember { Book.mock.first() }

//	ContainerTransformer(isVisible = isSmall) {
//		if (it) {
//			Card(
//				elevation = 4.dp,
//				shape = RoundedCornerShape(4.dp),
//				modifier = Modifier
//					.padding(8.dp)
//					.clickable(onClick = { setIsSmall(!isSmall) })
//			) {
//				Row {
//
//					Column {
//						Text(
//							text = book.title,
//							style = MaterialTheme.typography.h6
//						)
//						Text(
//							text = book.author,
//							style = MaterialTheme.typography.body2
//						)
//						Text(
//							text = "book_info_year_pages",
//							style = MaterialTheme.typography.body2
//						)
//					}
//				}
//			}
//		} else {
//			Card(
//				elevation = 4.dp,
//				shape = RoundedCornerShape(4.dp),
//				modifier = Modifier
//					.padding(8.dp)
//					.clickable(onClick = { setIsSmall(!isSmall) })
//			) {
//				Row {
//
//					Column {
//						Text(
//							text = book.title,
//							style = MaterialTheme.typography.h6
//						)
//						Text(
//							text = book.author,
//							style = MaterialTheme.typography.body2
//						)
//						Text(
//							text = "book_info_year_pages",
//							style = MaterialTheme.typography.body2
//						)
//						Text(
//							text = ContextAmbient.current.getString(R.string.dummy_long_string),
//							style = MaterialTheme.typography.body2
//						)
//					}
//				}
//			}
//		}
//	}

	Box(Modifier.padding(16.dp)) {
		val (isExpand, setIsExpand) = remember { mutableStateOf(false) }
		ContainerTransformer(isExpand) {
			Card(
				elevation = 4.dp,
				shape = RoundedCornerShape(4.dp),
				modifier = Modifier.padding(8.dp).clickable(onClick = { setIsExpand(!isExpand) })
			) {
				Row {

					ScrollableColumn {
						Text(
							text = book.title,
							style = MaterialTheme.typography.h6
						)
						Text(
							text = book.author,
							style = MaterialTheme.typography.body2
						)
						Text(
							text = "book_info_year_pages",
							style = MaterialTheme.typography.body2
						)
						Text(
							text = ContextAmbient.current.getString(R.string.dummy_long_string),
							style = MaterialTheme.typography.body2
						)
					}
				}
			}
		}
	}
}

@Composable
fun <T> CrossFadeCopy(
	current: T,
	modifier: Modifier = Modifier,
	animation: AnimationSpec<Float> = tween(1000),
	children: @Composable (T) -> Unit
) {
	val state = remember { MotionState<T>() }
	if (current != state.current) {
		state.current = current
		val keys = state.items.map { it.key }.toMutableList()
		if (!keys.contains(current)) {
			keys.add(current)
		}
		state.items.clear()
		keys.mapTo(state.items) { key ->
			MotionAnimationItem(key) { children ->
				val opacity = animatedOpacity(
					animation = animation,
					visible = key == current,
					onAnimationFinish = {
						if (key == state.current) {
							// leave only the current in the list
							state.items.removeAll { it.key != state.current }
							state.invalidate()
						}
					}
				)
				Box(Modifier.alpha(opacity.value)) {
					children()
				}
			}
		}
	}
	Box(modifier) {
		state.invalidate = invalidate
		state.items.fastForEach { (item, opacity) ->
			key(item) {
				opacity {
					children(item)
				}
			}
		}
	}

}

private class MotionState<T> {
	// we use Any here as something which will not be equals to the real initial value
	var current: Any? = Any()
	var items = mutableListOf<MotionAnimationItem<T>>()
	var invalidate: () -> Unit = { }
}

private data class MotionAnimationItem<T>(
	val key: T,
	val transition: @Composable (children: @Composable () -> Unit) -> Unit
)

@Composable
private fun animatedOpacity(
	animation: AnimationSpec<Float>,
	visible: Boolean,
	onAnimationFinish: () -> Unit = {}
): AnimatedFloat {
	val animatedFloat = animatedFloat(if (!visible) 1f else 0f)
	onCommit(visible) {
		animatedFloat.animateTo(
			if (visible) 1f else 0f,
			anim = animation,
			onEnd = { reason, _ ->
				if (reason == AnimationEndReason.TargetReached) {
					onAnimationFinish()
				}
			})
	}

	return animatedFloat
}

@Composable
fun ContainerTransformer(
	isExpand: Boolean,
	initialHeight: Int = 300,
	children: @Composable () -> Unit
) {
	val (constraint, setParentConstraint) = remember { mutableStateOf<Constraints?>(null) }
	val height = constraint?.maxHeight
	if (!isExpand) {
		Layout(
			content = children
		) { measureables, constraints ->
			Timber.v("no parent")
			if (constraint == null) setParentConstraint(constraints)

			val placeables = measureables.map { it.measure(constraints) }
			val maxWidth = placeables.fastMaxBy { it.width }?.width ?: 0
			Timber.v("no parent")
			layout(maxWidth, initialHeight) {
				placeables.fastForEach {
					it.place(0, 0)
				}
			}

		}
	} else if (height != null) {
		val animatedFloat = animatedFloat(initialHeight.toFloat())
		onCommit {
			animatedFloat.animateTo(height.toFloat(), anim = tween())
		}
		Layout(
			content = children
		) { measureables, constraints ->
			val placeables = measureables.map { it.measure(constraints) }
			val maxWidth = placeables.fastMaxBy { it.width }?.width ?: 0

			Timber.v("height: ${height}, constraints.maxHeight: ${constraints.maxHeight}, animatedFloat: ${animatedFloat.value}")
			layout(maxWidth, animatedFloat.value.toInt()) {
				placeables.fastForEach {
					it.place(0, 0)
				}
			}
		}
	}
}


fun Modifier.expandFullScreen() = Modifier.layout { measurable, constraints ->
	val placeable = measurable.measure(constraints)
	Timber.v("${placeable.width} ${placeable.height}")
	layout(placeable.width, placeable.height) {
		placeable.placeRelative(0, 0)
	}
}

