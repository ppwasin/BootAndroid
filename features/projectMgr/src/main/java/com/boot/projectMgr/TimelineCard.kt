package com.boot.projectMgr

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawBehind
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val linePosition = 60.dp

fun Modifier.drawLine() = this.drawBehind {
    drawLine(Color.Black, start = size.toRect().topRight, end = size.toRect().bottomRight, 4f)
}

@Composable
fun LineContainer(
    content: @Composable () -> Unit,
) {
    Row(
        Modifier
            .padding(end = 16.dp)
            .background(Color.Red)
//            .layout { measurable, constraints ->
//                val place = measurable.measure(constraints)
//                layout()
//            }
            .drawLine()
            .width(linePosition)
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
fun TimelineHeader() {
    Row {
        LineContainer {
            Text("Data")
        }
        Text("Tasks")
        Text("Show in days")
    }
}

@Composable
fun TimelineTask(task: Task) {
    Row {
        LineContainer {
            Text(task.timeCode)
        }
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
                        Icon(Icons.Default.AttachFile)
                        Text(task.attachmentCount.toString())
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("N\u00B0 ${task.id}")
                AvatarList(users = task.assignees, onUserClick = {}, size = 32.dp)
            }
        }
    }
}


