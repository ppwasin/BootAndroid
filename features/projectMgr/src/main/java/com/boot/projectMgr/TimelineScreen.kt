package com.boot.projectMgr

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.boot.projectMgr.ui.typography
import dev.chrisbanes.accompanist.coil.CoilImage

//Column == VStack
//Row == HStack
@Composable
fun TimelineScreen(project: Project = mockProject) {
    Column {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

            Text(project.title, style = typography.h1)
            Row {
                Text("${project.days} days", style = typography.body2)
                Text("|", modifier = Modifier.padding(horizontal = 4.dp), style = typography.body2)
                Text(project.date, style = typography.body2)
            }

            Row(
                Modifier.padding(top = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AvatarList(
                    users = project.users,
                    onUserClick = {},
                    onAddClick = {}
                )
                ProjectProgressIndicator(progress = project.progress, status = project.status)
            }
        }

        Column(Modifier
            .background(Color(0xFFF1F5FE), shape = RoundedCornerShape(topLeft = 40.dp))
            .padding(top = 40.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
        ) {
            Text("Data")
        }
    }
}

private fun Modifier.layoutOffset(x: Dp = 0.dp, y: Dp = 0.dp) =
    this then layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width + x.toIntPx(), placeable.height + y.toIntPx()) {
            placeable.placeRelative(x.toIntPx(), y.toIntPx())
        }
    }

@Composable
fun AvatarList(
    users: List<User>,
    modifier: Modifier = Modifier,
    onUserClick: (User) -> Unit,
    onAddClick: (() -> Unit)? = null,
) {
    Row(modifier) {
        users.forEachIndexed { index, user ->
            StackButton(
                onClick = { onUserClick(user) },
                isStack = index != 0
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
            StackButton(onClick = onAddClick, isStack = users.isNotEmpty()) {
                Icon(Icons.Filled.Add)
            }
        }
    }
}

@Composable
fun StackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isStack: Boolean = false,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .layoutOffset(x = if (!isStack) 0.dp else -12.dp)
            .drawShadow(5.dp, shape = CircleShape, clip = true)
            .background(Color.White, CircleShape)
            .size(44.dp),
        icon = content
    )

}