package com.boot.compose.bottombar.screen

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CountScreen(name: String, count: Int, setCount: (Int) -> Unit) {
    ConstraintLayout(modifier = Modifier.padding(8.dp)) {
        val (text, button) = createRefs()
        val buttonConstraint = Modifier.constrainAs(button) {
            top.linkTo(parent.top)
        }
        val textConstraint = Modifier.constrainAs(text) {
            top.linkTo(button.bottom, 8.dp)
        }

        Button(
            onClick = { setCount(count + 1) },
            modifier = buttonConstraint
        ) {
            Text("count up")
        }

        Text(
            text = "Hello $name! $count",
            modifier = textConstraint
        )
    }
}