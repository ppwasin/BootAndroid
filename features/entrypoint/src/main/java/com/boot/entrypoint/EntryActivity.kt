package com.boot.entrypoint

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.boot.entrypoint.ui.BootAndroidTheme
import com.google.android.play.core.splitcompat.SplitCompat


class EntryActivity : AppCompatActivity() {
	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BootAndroidTheme {
				// A surface container using the 'background' color from the theme
				Scaffold(
					topBar = { AppTopbar() },
					bottomBar = { AppBottomNavigation() }
				) {

					Greeting("Android")

				}
			}
		}
	}
}

@Composable
fun Greeting(name: String) {
	Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	BootAndroidTheme {
		Greeting("Android")
	}
}

class NavigationItem(val label: String, val icon: VectorAsset)

@Composable
fun AppBottomNavigation() {
	val selectedItem = remember { mutableStateOf(0) }
	val items = listOf(
		NavigationItem("Call", Icons.Filled.Call),
		NavigationItem("People", Icons.Filled.Face),
		NavigationItem("Email", Icons.Filled.Email)
	)
//	Column {
//		bodyContent()
//		Spacer(modifier = Modifier.preferredHeight(64.dp))
	BottomNavigation {
		items.forEachIndexed { index, item ->
			BottomNavigationItem(
				icon = { Icon(item.icon) },
				label = { Text(text = item.label) },
				selected = selectedItem.value == index,
				onSelect = { selectedItem.value = index }
			)
		}
	}
//	}
}

@Composable
fun AppTopbar() {
	TopAppBar(
		title = {
			Text(text = "Jetpack Compose")
		},
//		navigationIcon = {
//			IconButton(onClick = { }) {
//				Icon(Icons.Filled.ArrowBack)
//			}
//		}
	)
}