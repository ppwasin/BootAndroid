package com.boot.entrypoint.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import com.boot.entrypoint.MainScreen

@Composable
fun AppBottomNavigation(
	screen: MainScreen,
	setScreen: (MainScreen) -> Unit
) {
	BottomNavigation {
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Call) },
			label = { Text("Call") },
			selected = screen == MainScreen.Page1,
			onSelect = { setScreen(MainScreen.Page1) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Face) },
			label = { Text("People") },
			selected = screen == MainScreen.Page2,
			onSelect = { setScreen(MainScreen.Page2) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Email) },
			label = { Text("Email") },
			selected = screen == MainScreen.Page3,
			onSelect = { setScreen(MainScreen.Page3) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Email) },
			label = { Text("Demo") },
			selected = screen == MainScreen.Page4,
			onSelect = { setScreen(MainScreen.Page4) }
		)
	}
}