package com.boot.entrypoint

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.boot.entrypoint.components.AppBottomNavigation
import com.boot.entrypoint.components.AppScaffold
import com.boot.entrypoint.page.Book
import com.boot.entrypoint.page.BooksScreen
import com.boot.entrypoint.page.News
import com.boot.entrypoint.ui.BootAndroidTheme
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router
import com.github.zsoltk.compose.savedinstancestate.BundleScope
import com.github.zsoltk.compose.savedinstancestate.saveAmbient
import com.google.android.play.core.splitcompat.SplitCompat


class EntryActivity : AppCompatActivity() {
	private val backPressHandler = BackPressHandler()
	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BootAndroidTheme {
				Providers(
					AmbientBackPressHandler provides backPressHandler,
				) {
//					val (screen, setScreen) = remember { mutableStateOf(MainScreen.Page1) }
					BundleScope(savedInstanceState) {
						Router(MainScreen.Page1) { backStack ->
							val screen = backStack.last()
							val setScreen = backStack::push
							val (count, setCount) = remember { mutableStateOf(0) }
							val newsScrollState = rememberScrollState(0f)

							Scaffold(
								bottomBar = { AppBottomNavigation(screen, setScreen) }
							) { innerPadding ->
								Box(modifier = Modifier.padding(innerPadding)) {
									when (screen) {
										MainScreen.Page1 -> BooksScreen(books = Book.mock)
										MainScreen.Page2 -> News.Content(newsScrollState)
										MainScreen.Page3 -> Greeting(name = "3", count, setCount)
									}
								}
							}

						}


					}
				}

			}
		}
	}

	override fun onBackPressed() {
		if (!backPressHandler.handle()) {
			super.onBackPressed()
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.saveAmbient()
	}
}

@Composable
fun Greeting(name: String, count: Int, setCount: (Int) -> Unit) {
	AppScaffold(title = "Sample remember state") {
		ConstraintLayout(modifier = Modifier.padding(8.dp)) {
			val (text, button) = createRefs()
			val buttonConstraint = Modifier.constrainAs(button) {
				top.linkTo(parent.top)
			}
			val textConstraint = Modifier.constrainAs(text) {
				top.linkTo(button.bottom, 8.dp)
			}

			Button(
				onClick = { setCount(count + 1) },
				modifier = buttonConstraint
			) {
				Text("count up")
			}

			Text(
				text = "Hello $name! $count",
				modifier = textConstraint
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	BootAndroidTheme {
		Greeting("Android", 0) {}
	}
}
