package com.boot.projectMgr.entry

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.boot.projectMgr.ui.typography

//Column == VStack
//Row == HStack
@Composable
fun TimelineScreen(project: Project = mockProject) {
    ScrollableColumn {
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
                    onAddClick = {},
                    size = 44.dp
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
            TimelineHeader()
            for (index in project.tasks.indices) {
                val task = project.tasks[index]
                val prev = project.tasks.getOrNull(index - 1)
                val next = project.tasks.getOrNull(index + 1)

                TimelineTask(
                    task = task,
                    topLineState = when {
                        prev == null -> LineState.Undefined
                        task.timeCode == prev.timeCode -> LineState.In
                        else -> LineState.Out
                    },
                    bottomLineState = when {
                        next == null -> LineState.Undefined
                        task.timeCode == next.timeCode -> LineState.In
                        else -> LineState.Out
                    },
                )
            }
        }
    }
}

