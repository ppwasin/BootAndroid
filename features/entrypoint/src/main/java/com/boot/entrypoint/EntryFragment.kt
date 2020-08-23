package com.boot.entrypoint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import com.boot.entrypoint.ui.BootAndroidTheme

class EntryFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		val fragmentView = inflater.inflate(R.layout.fragment_empty, container, false)

		(fragmentView as ViewGroup).setContent(Recomposer.current()) {
			BootAndroidTheme {
				// A surface container using the 'background' color from the theme
				Surface {
					Greeting("Android")
				}
			}
		}
		return fragmentView
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
}