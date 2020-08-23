package com.boot.entrypoint.sample

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.atMost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp

/*
More example:
- Barrier, Spread, Chain: https://medium.com/android-dev-hacks/exploring-constraint-layout-in-jetpack-compose-67b82123c28b
 */
@Composable
fun DemoInlineDSL() {
	ConstraintLayout {
		val (text1, text2, text3) = createRefs()

		Text("Text1", Modifier.constrainAs(text1) {
			start.linkTo(text2.end, margin = 20.dp)
		})
		Text("Text2", Modifier.constrainAs(text2) {
			centerTo(parent)
		})

		val barrier = createBottomBarrier(text1, text2)
		Text("This is a very long text", Modifier.constrainAs(text3) {
			top.linkTo(barrier, margin = 20.dp)
			centerHorizontallyTo(parent)
			width = Dimension.preferredWrapContent.atMost(40.dp)
		})
	}
}

@Composable
fun DemoConstraintSet() {
	ConstraintLayout(ConstraintSet {
		val text1 = createRefFor("text1")
		val text2 = createRefFor("text2")
		val text3 = createRefFor("text3")

		constrain(text1) {
			start.linkTo(text2.end, margin = 20.dp)
		}
		constrain(text2) {
			centerTo(parent)
		}

		val barrier = createBottomBarrier(text1, text2)
		constrain(text3) {
			top.linkTo(barrier, margin = 20.dp)
			centerHorizontallyTo(parent)
			width = Dimension.preferredWrapContent.atMost(40.dp)
		}
	}) {
		Text("Text1", Modifier.layoutId("text1"))
		Text("Text2", Modifier.layoutId("text2"))
		Text("This is a very long text", Modifier.layoutId("text3"))
	}
}