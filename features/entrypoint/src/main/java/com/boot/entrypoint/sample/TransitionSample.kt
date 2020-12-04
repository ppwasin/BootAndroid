package com.boot.entrypoint.sample

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp


enum class StateAnim {
    First, Second
}

val colorKey = ColorPropKey()
val widthKey = DpPropKey()
val heightKey = DpPropKey()

val definition = transitionDefinition<StateAnim> {
    state(StateAnim.First) {
        this[colorKey] = Color.Red
        this[widthKey] = 200.dp
        this[heightKey] = 400.dp
    }
    state(StateAnim.Second) {
        this[colorKey] = Color.Green
        this[widthKey] = 300.dp
        this[heightKey] = 300.dp
    }
}

@Composable
fun TransitionBasedColoredRect() {
    // This puts the transition in State.First. Any subsequent state change will trigger a
    // transition animation, as defined in the transition definition.
    val state = transition(definition = definition, toState = StateAnim.First)
    Box(
        Modifier
            .preferredSize(state[widthKey], state[heightKey])
            .background(color = state[colorKey])
    )
}

@Composable
fun ColorRectWithInitState() {
    // This starts the transition going from State.First to State.Second when this composable
    // gets composed for the first time.
    val state = transition(
        definition = definition, initState = StateAnim.First, toState = StateAnim.Second
    )
    Box(
        Modifier
            .preferredSize(state[widthKey], state[heightKey])
            .background(state[colorKey])
    )
}

@Composable
fun SnapSample() {
    val sizeState = FloatPropKey()
    val toState = remember { mutableStateOf("A") }

    val definition = transitionDefinition<String> {
        state("A") { this[sizeState] = 50f }
        state("B") { this[sizeState] = 175f }

        snapTransition("A" to "B")
        snapTransition("B" to "A")
    }
    val state = transition(definition = definition, toState = toState.value) {

    }

    Canvas(modifier = Modifier.preferredSize(80.dp)) {
        drawCircle(Color.Red, state[sizeState])
    }

    Button(onClick = {
        if (toState.value == "A") {
            toState.value = "B"
        } else {
            toState.value = "A"
        }
    }, modifier = Modifier.padding(16.dp)) {
        Text("Animate!")
    }
}


private val rotation = FloatPropKey()
private val rotationTransitionDefinition = transitionDefinition<String> {
    // We define a transitionDefinition that's meant to be an exhaustive list of all states &
    // state transitions that are a part of your animation. Below, we define two states - state 0
    // & state 360. For each state, we also define the value of the properties when they are in
    // the respective state. For example - for state A, we assign the rotation prop the value 0f
    // and for state B, we assign the rotation prop the value 360f.
    state("A") { this[rotation] = 0f }
    state("B") { this[rotation] = 360f }

    // Here we define the transition spec i.e what action do we need to do as we transition from
    // one state to another. Below, we define a TransitionSpec for the transition
    // state A -> state B.
    transition(fromState = "A", toState = "B") {
        // For the transition from state A -> state B, we assign a AnimationBuilder to the
        // rotation prop where we specify how we want to update the value of the rotation prop
        // between state A & B, what the duration of this animation should be, what kind of
        // interpolator to use for the animation & how many iterations of this animation are needed.
        // Since we want the rotation to be continous, we use the repeatable AnimationBuilder and
        // set the iterations to Infinite.
        rotation using repeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutLinearInEasing
            ),
            iterations = Infinite
        )
    }
}

@Composable
fun RotateSample() {
// Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. We give it a ContentGravity of Center to ensure the children of this composable
    // are placed in its center. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, as the Box composable to
    // occupy the entire available height & width using Modifier.fillMaxSize().
    Box(modifier = Modifier.fillMaxSize()) {
        // Transition composable creates a state-based transition using the animation configuration
        // defined in [TransitionDefinition]. In the example below, we use the
        // rotationTransitionDefinition that we discussed above and also specify the initial
        // state of the animation & the state that we intend to transition to. The expectation is
        // that the transitionDefinition allows for the transition from the "initialState" to the
        // "toState".
        val state = transition(
            definition = rotationTransitionDefinition,
            initState = "A",
            toState = "B"
        )
        // We use the Canvas composable that gives you access to a canvas that you can draw
        // into. We also pass it a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that are used
        // to modify the composable that its applied to. In this example, we assign a size
        // of 200dp to the Canvas using Modifier.preferredSize(200.dp).
        Canvas(modifier = Modifier.preferredSize(200.dp)) {
            // As the Transition is changing the interpolating the value of your props based
            // on the "from state" and the "to state", you get access to all the values
            // including the intermediate values as they are being updated. We can use the
            // state variable and access the relevant props/properties to update the relevant
            // composables/layouts. Below, we use state[rotation] to get the latest value of
            // rotation (it will be a value between 0 & 360 depending on where it is in the
            // transition) and use it to rotate our canvas.
            rotate(state[rotation]) {
                drawRect(color = Color(255, 138, 128))
            }
        }
    }
}
