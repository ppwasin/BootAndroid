package com.boot.projectMgr

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun TimelineScreen(project: Project = mockProject) {
    Column {
        Text(project.title, style = typography.h1)
        Row {
            Text("${project.days} days", style = typography.body2)
            Text("|", modifier = Modifier.padding(horizontal = 4.dp), style = typography.body2)
            Text(project.date, style = typography.body2)
        }

        AvatarList(users = project.users)
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
fun AvatarList(users: List<User>) {
    Row {
        users.forEachIndexed { index, user ->
            Avatar(
                user = user,
                modifier = Modifier.layoutOffset(x = if (index == 0) 0.dp else -10.dp)
            )
        }
    }
}

@Composable
fun Avatar(user: User, modifier: Modifier = Modifier) {
    CoilImage(
        user.imageUrlForSize(with(DensityAmbient.current) { 48.dp.toIntPx() }),
        modifier = modifier
            .drawShadow(5.dp, shape = CircleShape, clip = true)
            .background(Color.White, CircleShape)
            .padding(2.dp)
//            .background(Color(0xFF7671D5), CircleShape)
            .clip(CircleShape)
            .size(48.dp)
    )
}