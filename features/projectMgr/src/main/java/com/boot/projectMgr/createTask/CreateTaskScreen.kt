package com.boot.projectMgr.createTask

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.boot.projectMgr.entry.AvatarList
import com.boot.projectMgr.entry.mockProject
import com.boot.projectMgr.ui.bgColor
import com.boot.projectMgr.ui.primaryGreen
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CreateTaskScreen() {
	//	var name by namesFlow.collectAsState(initial = "Bob")
	var projectName by remember { mutableStateOf("Create Additional pages") }
	var client by remember { mutableStateOf(mockClients.first()) }
	var startDate by remember { mutableStateOf("") }
	var endDate by remember { mutableStateOf("") }
	var category by remember { mutableStateOf("Design") }
	var attachments = remember { mutableStateListOf(mockAttachment) }
	ScrollableColumn {
		Surface(
			color = bgColor,
		) {
			Column {
				Column(
					Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
					Field("CLIENT") {
						ClientDropdownField(
							selectedValue = client,
							values = mockClients,
							onValueChange = { client = it },
						)
					}
					
					
					Field("PROJECT NAME") {
						PMTextField(
							modifier = Modifier.weight(1f),
							value = projectName,
							onValueChange = { projectName = it }
						)
					}
					
					
					Field("START/END DATES") {
						DatePicker(value = startDate, onValueChange = { startDate = it })
						Text(" - ")
						DatePicker(value = endDate, onValueChange = { endDate = it })
					}
					
					
					Field("ASSIGNEE") {
						AvatarList(
							size = 40.dp,
							users = mockProject.users,
							onUserClick = {},
							onAddClick = {}
						)
					}
					
					
					Field("CATEGORY") {
						RadioChips(
							selectedValue = category,
							values = listOf("Design", "Frontend", "Backend"),
							onSelectedChange = { category = it }
						)
					}
				}
				Surface(
					shape = RoundedCornerShape(topLeft = 24.dp, topRight = 24.dp),
					color = Color.White
				) {
					Column(
						Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
						Text("Description")
						IconButton(onClick = {}) {
							Icon(Icons.Default.Attachment, tint = primaryGreen)
						}
						Text("ATTACHMENTS")
						for (attachment in attachments) {
							AttachmentProgress(attachment = attachment)
						}
						Button(
							modifier = Modifier.fillMaxWidth(),
							onClick = {}
						) {
							Text("CREATE TASK")
						}
					}
					
				}
			}
			
		}
	}
}

@Composable
inline fun Field(label: String, content: @Composable RowScope.() -> Unit) {
	Column(Modifier.padding(vertical = 8.dp)) {
		Text(
			label,
			color = Color.White.copy(alpha = 0.7f),
			fontSize = 10.sp,
			modifier = Modifier.padding(bottom = 8.dp)
		)
		Row { content() }
	}
	
}

@Composable
fun RadioChips(
	selectedValue: String,
	values: List<String>,
	onSelectedChange: (String) -> Unit,
) {
	val selectedColor = ButtonConstants.defaultButtonColors(
		backgroundColor = primaryGreen,
		contentColor = Color.White
	)
	val unselectedColor = ButtonConstants.defaultButtonColors(backgroundColor = Color.White)
	Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
		for (value in values) {
			val isSelected = value == selectedValue
			Button(
				contentPadding = PaddingValues(
					start = 6.dp,
					end = 20.dp,
					top = 4.dp,
					bottom = 4.dp
				),
				colors = if (isSelected) selectedColor else unselectedColor,
				shape = RoundedCornerShape(8.dp),
				onClick = { onSelectedChange(value) },
			) {
				if (isSelected)
					Icon(
						imageVector = Icons.Default.Check,
						modifier = Modifier.size(14.dp)
					)
				else
					Spacer(modifier = Modifier.size(14.dp))
				Text(
					value,
					modifier = Modifier.padding(start = 2.dp),
					color = if (isSelected) Color.White else Color.Black
				)
			}
		}
	}
}

@Composable
fun ClientDropdownField(
	selectedValue: Client,
	onValueChange: (Client) -> Unit,
	values: List<Client>,
) {
	Row(verticalAlignment = Alignment.CenterVertically) {
		CoilImage(
			modifier = Modifier
				.size(48.dp)
				.clip(CircleShape)
				.background(Color.White),
			data = "https://i.pravatar.cc/200?img=2"
		)
		Spacer(modifier = Modifier.size(6.dp))
		PMTextField(
			value = selectedValue.name,
			onValueChange = { newName ->
				onValueChange(selectedValue.copy(name = newName))
			}
		)
	}
}

@Composable
fun DatePicker(
	modifier: Modifier = Modifier,
	value: String,
	onValueChange: (String) -> Unit,
) {
	PMTextField(modifier = modifier, value = value, onValueChange = onValueChange)
}

@Composable
fun PMTextField(
	modifier: Modifier = Modifier,
	value: String,
	onValueChange: (String) -> Unit,
) {
	TextField(
		modifier = modifier,
		value = value,
		onValueChange = onValueChange,
		textStyle = TextStyle(color = Color.White),
		activeColor = Color.White,
		inactiveColor = Color.White.copy(alpha = 0.7f),
		backgroundColor = bgColor
	)
}

//https://youtu.be/pFE70Xbpmno?t=5403