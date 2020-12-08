package com.boot.compose.bottombar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.boot.compose.bottombar.ui.BootAndroidTheme

/*Source: https://proandroiddev.com/implement-bottom-bar-navigation-in-jetpack-compose-b530b1cd9ee2
- Done
    - nav without argument
- Pause
    - nav with argument. Reason: Compare compose-nav with router. Router is more easy to pass any type of value.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BootAndroidTheme {
        MainScreen()
    }
}