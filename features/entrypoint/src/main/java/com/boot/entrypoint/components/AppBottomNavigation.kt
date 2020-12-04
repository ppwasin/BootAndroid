package com.boot.entrypoint.components

import androidx.compose.foundation.Icon
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
			onClick = { setScreen(MainScreen.Page1) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Face) },
			label = { Text("People") },
			selected = screen == MainScreen.Page2,
			onClick = { setScreen(MainScreen.Page2) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Email) },
			label = { Text("Email") },
			selected = screen == MainScreen.Page3,
			onClick = { setScreen(MainScreen.Page3) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.VideogameAsset) },
			label = { Text("Animation") },
			selected = screen == MainScreen.Page4,
			onClick = { setScreen(MainScreen.Page4) }
		)
		BottomNavigationItem(
			icon = { Icon(Icons.Filled.Book) },
			label = { Text("Book") },
			selected = screen == MainScreen.Book,
			onClick = { setScreen(MainScreen.Book) }
		)
	}
}