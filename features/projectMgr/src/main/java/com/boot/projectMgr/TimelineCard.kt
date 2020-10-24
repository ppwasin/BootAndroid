package com.boot.projectMgr

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawBehind
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

val linePosition = 80.dp

fun Modifier.drawLine() = this.drawBehind {
    drawLine(Color.Black, start = size.toRect().topRight, end = size.toRect().bottomRight, 4f)
}

@Composable
fun TimelineRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
) {
    Layout(children = {
        Row(
            Modifier
                .padding(end = 16.dp)
                .drawLine(),
            verticalAlignment = Alignment.CenterVertically
        ) { leftContent() }
        Row {
            rightContent()
        }
    }) { (left, right), constraint ->
        val leftWidth = linePosition.toIntPx()
        val placeRight = right.measure(
            Constraints(
                minWidth = (constraint.minWidth - leftWidth).coerceAtLeast(0),
                maxWidth = (constraint.maxWidth - leftWidth).coerceAtLeast(0),
                minHeight = constraint.minHeight,
                maxHeight = constraint.maxHeight)
        )

        val placeLeft = left.measure(
            Constraints.fixed(width = leftWidth, height = placeRight.height)
        )
        layout(placeLeft.width + placeRight.width, placeRight.height) {
            placeLeft.placeRelative(0, 0)
            placeRight.placeRelative(leftWidth, 0)
        }
    }
}


@Composable
fun TimelineHeader() {
    TimelineRow(
        leftContent = { Text("Data") }
    ) {
        Text("Tasks")
        Text("Show in days")
    }

}

@Composable
fun TimelineTask(task: Task) {
    TimelineRow(leftContent = { Text(task.timeCode) }) {
        Column(
            Modifier
                .padding(vertical = 8.dp)
                .background(Color.White, RoundedCornerShape(6.dp))
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    task.status.label,
                    color = task.status.color
                )

                Text(task.tag)
            }
            Row {
                Text(task.title)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = {}) {
                    Row {
                        Icon(Icons.Default.ChatBubbleOutline)
                        Text(task.commentCount.toString())
                    }
                }
                IconButton(onClick = {}) {
                    Row {
                        Icon(Icons.Default.Attachment)
                        Text(task.attachmentCount.toString())
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("N\u00B0 ${task.id}")
                Spacer(Modifier.width(2.dp))
                AvatarList(users = task.assignees, onUserClick = {}, size = 32.dp)
            }
        }
    }
}


