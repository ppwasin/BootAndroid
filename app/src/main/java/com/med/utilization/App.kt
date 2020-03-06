package com.med.utilization

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat

class App : Application(), AppDiProvider {

	override val featureSyntax by lazy { AppFeatureInstaller(this@App) }

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}

}