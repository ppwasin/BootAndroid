package com.boot.projectMgr.createTask

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.boot.projectMgr.entry.AvatarList
import com.boot.projectMgr.entry.mockProject

val bgColor = Color(0xFF33354E)

@Composable
fun CreateTaskScreen() {
	//	var name by namesFlow.collectAsState(initial = "Bob")
	val (projectName, setProjectName) = remember { mutableStateOf("Create Addtional pages") }
	val (client, setClient) = remember { mutableStateOf(mockClients.first()) }
	val (startDate, setStartDate) = remember { mutableStateOf("") }
	val (endDate, setEndDate) = remember { mutableStateOf("") }
	val (category, setCategory) = remember { mutableStateOf("Design") }
	
	ScrollableColumn(
		Modifier
			.background(bgColor)
			.padding(horizontal = 16.dp, vertical = 20.dp)
	) {
		FieldLabel("CLIENT")
		ClientDropdownField(
			selectedValue = client,
			values = mockClients,
			onValueChange = setClient,
		)
		FieldLabel("PROJECT NAME")
		PMTextField(value = projectName, onValueChange = setProjectName)
		FieldLabel("START/END DATES")
		Row {
			DatePicker(value = startDate, onValueChange = setStartDate)
			DatePicker(value = endDate, onValueChange = setEndDate)
		}
		FieldLabel("ASSIGNEE")
		AvatarList(
			size = 40.dp,
			users = mockProject.users,
			onUserClick = {},
			onAddClick = {}
		)
		FieldLabel("CATEGORY")
		RadioChips(
			selectedValue = category,
			values = listOf("Design", "Frontend", "Backend"),
			onSelectedChange = setCategory)
	}
}

@Composable
fun FieldLabel(label: String) {
	Text(label)
}

@Composable
fun RadioChips(
	selectedValue: String,
	values: List<String>,
	onSelectedChange: (String) -> Unit,
) {
	Row {
		for (value in values) {
			val isSelected = value == selectedValue
			IconButton(
				modifier = Modifier.background(if (isSelected) Color.Green else Color.White),
				onClick = { onSelectedChange(value) }) {
				Row {
					if (isSelected) Icon(Icons.Default.Check)
					Text(value, color = if (isSelected) Color.White else Color.Black)
				}
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

}

@Composable
fun DatePicker(
	value: String,
	onValueChange: (String) -> Unit,
) {
	TextField(value = value, onValueChange = onValueChange)
}

@Composable
fun PMTextField(
	value: String,
	onValueChange: (String) -> Unit,
) {
	TextField(value = value, onValueChange = onValueChange)
}