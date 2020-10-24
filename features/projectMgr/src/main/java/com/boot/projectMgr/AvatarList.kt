package com.boot.projectMgr

import androidx.compose.foundation.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AvatarList(
    size: Dp,
    users: List<User>,
    modifier: Modifier = Modifier,
    onUserClick: (User) -> Unit,
    onAddClick: (() -> Unit)? = null,
) {
    Row(modifier) {
        users.forEachIndexed { index, user ->
            StackButton(
                onClick = { onUserClick(user) },
                isStack = index != 0,
                size = size
            ) {
                CoilImage(
                    user.imageUrlForSize(with(DensityAmbient.current) { 48.dp.toIntPx() }),
                    modifier = Modifier
                        .drawShadow(5.dp, shape = CircleShape, clip = true)
                        .background(Color.White, CircleShape)
                        .padding(2.dp)
                        .clip(CircleShape)
                        .fillMaxSize()
                )
            }
        }
        if (onAddClick != null) {
            StackButton(onClick = onAddClick, isStack = users.isNotEmpty(), size = size) {
                Icon(Icons.Filled.Add)
            }
        }
    }
}


@Composable
fun StackButton(
    size: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStack: Boolean = false,
    content: @Composable () -> Unit,
) {
    val overlap = size / 4
    IconButton(
        onClick = onClick,
        modifier = modifier
            .layoutOffset(x = if (!isStack) 0.dp else -overlap)
            .drawShadow(5.dp, shape = CircleShape, clip = true)
            .background(Color.White, CircleShape)
            .size(size),
        icon = content
    )

}

private fun Modifier.layoutOffset(x: Dp = 0.dp, y: Dp = 0.dp) =
    this then layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width + x.toIntPx(), placeable.height + y.toIntPx()) {
            placeable.placeRelative(x.toIntPx(), y.toIntPx())
        }
    }