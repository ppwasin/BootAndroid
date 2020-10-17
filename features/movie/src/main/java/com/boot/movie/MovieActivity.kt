package com.boot.movie

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.boot.movie.ui.DogfoodTheme
import com.google.android.play.core.splitcompat.SplitCompat


class MovieActivity : AppCompatActivity() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogfoodTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Screen()
                }
            }
        }
    }


}
