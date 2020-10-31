package com.boot.projectMgr.entry

import android.graphics.DashPathEffect
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

val linePosition = 80.dp
val timelineDotSize = 8.dp
val dashPathEffect = DashPathEffect(floatArrayOf(20f, 10f), 0f)

enum class LineState(val color: Color, val stroke: Stroke) {
    Undefined(Color.Gray, Stroke(width = 1f, pathEffect = dashPathEffect)), // dotted line
    In(Color.White, Stroke(width = 8f)), // solid white
    Out(Color.Gray, Stroke(width = 2f)) // solid gray
}

fun Modifier.drawLine(
    status: Status?,
    top: LineState,
    bottom: LineState,
) = this.drawBehind {
    val centerPosition = Offset(x = size.width, y = size.height / 2f)
    val topRight = Offset(size.width, 0f)
    val bottomRight = Offset(size.width, size.height)
    // Line Top
    drawLine(
        color = top.color,
        start = topRight,
        end = centerPosition,
        strokeWidth = top.stroke.width,
        pathEffect = top.stroke.pathEffect
    )
    // Line Bottom
    drawLine(
        color = bottom.color,
        start = centerPosition,
        end = bottomRight,
        strokeWidth = bottom.stroke.width,
        pathEffect = bottom.stroke.pathEffect
    )
    if (status != null) {
        drawCircle(
            Color.White,
            radius = timelineDotSize.toPx(),
            center = centerPosition
        )
        drawCircle(
            status.color,
            radius = (timelineDotSize - 2.dp).toPx(),
            center = centerPosition
        )
    }
}

@Composable
fun TimelineRow(
    status: Status?,
    topLineState: LineState,
    bottomLineState: LineState,
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit,
) {
    Layout(children = {
        Row(
            Modifier
                .padding(end = 16.dp)
                .drawLine(status, topLineState, bottomLineState),
            verticalAlignment = Alignment.CenterVertically
        ) { leftContent() }
        Row {
            rightContent()
        }
    }) { (left, right), constraint ->
        val leftWidth = linePosition.toIntPx()

        //remove with leftWidth because, right children default to full width
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
        status = null,
        topLineState = LineState.Undefined,
        bottomLineState = LineState.Undefined,
        leftContent = { Text("Data") }
    ) {
        Text("Tasks")
        Text("Show in days")
    }

}

@Composable
fun TimelineTask(
    task: Task,
    topLineState: LineState,
    bottomLineState: LineState,
) {
    TimelineRow(
        status = task.status,
        topLineState = topLineState,
        bottomLineState = bottomLineState,
        leftContent = { Text(task.timeCode) }
    ) {
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


