package com.boot.entrypoint.sample.material_motion

import android.annotation.SuppressLint
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.util.annotation.IntRange
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule


enum class VerticalTransformState {
	Small, Big
}

val sizePercentState = FloatPropKey()
val alphaState = FloatPropKey()
val widthState = FloatPropKey()
val heightState = FloatPropKey()


@Composable
fun VerticalTransform(
	onStateChangeFinished: (() -> Unit)? = null,
	content: @Composable () -> Unit,
) {
//	val (isAnimating, setIsAnimating) = remember { mutableStateOf(false) }
	val containerDefinition = remember { createContainerDefinition(smallSize = 0f, bigSize = 100f) }

	val (toState, setToState) = remember { mutableStateOf(VerticalTransformState.Small) }

	val transitionState = transition(
		definition = containerDefinition,
		initState = VerticalTransformState.Small,
		toState = toState,
//		onStateChangeFinished = {}
	)
//	Layout(children = content){ measureables, constraints ->
//
//	}


	Timber.v("transitionState[sizeState].dp: ${transitionState[sizePercentState]}")
//	Layout(
//		children = content,
//		modifier = Modifier.size(transitionState[sizeState].dp) + Modifier.clickable(
//			onClick = {
//				setToState(
//					ContainerTransformState.Big
//				)
//				Timer().schedule(3000) {
//					onStateChangeFinished?.invoke()
//				}
//
//			})
//	) { measureables, constraints ->
//		val placeables = measureables.map { it.measure(constraints) }
//		val maxWidth = placeables.fastMaxBy { it.width }?.width ?: 0
//		val maxHeight = placeables.fastMaxBy { it.height }?.height ?: 0
//
//		val offset = IntOffset.Zero
//		val animatedSize = IntSize(maxWidth, maxHeight)
//
////		// If animation has finished update state
////		if (!isAnimating) {
////			if (toState == ContainerTransformState.Small) {
////				setToState(ContainerTransformState.Big)
////			}
////		}
//
//		Timber.v("measureables: $measureables")
//		Timber.v("constraints: $constraints")
//		Timber.v("placeables: $placeables")
//		Timber.v("inside: ${animatedSize.width}, ${animatedSize.width.dp}, maxWidth: ${maxWidth.dp}")
//		Timber.v("constraints.maxWidth: ${constraints.maxWidth.dp}")
//		Timber.v("offset: $offset")
//		Timber.v(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
//		// Position the children.
//		layout(animatedSize.width, animatedSize.height) {
//			placeables.fastForEach {
//				it.place(offset.x, offset.y)
//			}
//		}
//	}


	val modifier = Modifier.clickable(
		onClick = {
			setToState(
				VerticalTransformState.Big
			)
			Timer().schedule(3000) {
				onStateChangeFinished?.invoke()
			}
		})
//		.preferredHeight(IntrinsicSize.Max)

	Layout(children = content, modifier = modifier) { measurables, constraints ->
		layout(constraints.maxWidth, constraints.maxHeight) {
//			val halfHeight = constraints.maxHeight / 2
//			val childConstraints = constraints.copy(
//				minHeight = minOf(constraints.minHeight, halfHeight),
//				maxHeight = halfHeight
//			)
//			require(measurables.size == 2)
			val child = measurables[0].measure(constraints)
			Timber.v(
				"""\n
				- constraints.min(H,W): ${constraints.minHeight}, ${constraints.minWidth}
				- constraints.max(H,W): ${constraints.maxHeight}, ${constraints.maxWidth}
				- child(H,W): ${child.height}, ${child.width}
			""".trimIndent()
			)
			child.place(0, 0)
		}
	}
}


@SuppressLint("Range")
fun createContainerDefinition(
	smallSize: Float,
	bigSize: Float,
	@IntRange(from = 0)
	duration: Int = AnimationConstants.DefaultDurationMillis
) =
	transitionDefinition<VerticalTransformState> {
		state(VerticalTransformState.Small) {
			this[sizePercentState] = smallSize
			this[alphaState] = 0f
		}
		state(VerticalTransformState.Big) {
			this[sizePercentState] = bigSize
			this[alphaState] = 1f
		}
		transition(
			fromState = VerticalTransformState.Small,
			toState = VerticalTransformState.Big
		) {

			sizePercentState using keyframes {
				durationMillis = duration
				smallSize at 0 // smallSize at frame 0
				bigSize at duration // bigSize at frame 400
			}
//			sizeState using tween(
//				durationMillis = 300,
//				easing = FastOutLinearInEasing
//			)

			alphaState using keyframes {
				durationMillis = duration
				0f at 0
				0.1f at 225
				1f at duration
			}
		}

	}
