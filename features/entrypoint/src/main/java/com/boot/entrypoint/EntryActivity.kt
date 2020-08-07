package com.boot.entrypoint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.boot.entrypoint.ui.BootAndroidTheme


class EntryActivity : AppCompatActivity() {
//	override fun attachBaseContext(base: Context?) {
//		super.attachBaseContext(base)
//		SplitCompat.install(this)
//	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BootAndroidTheme {
				// A surface container using the 'background' color from the theme
				Surface {
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