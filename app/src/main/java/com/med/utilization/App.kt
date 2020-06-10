package com.med.utilization

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.med.utilization.di.AppComponent
import com.med.utilization.di.AppDiProvider
import com.med.utilization.di.AppModule
import com.med.utilization.di.DaggerAppComponent

class App : Application(), AppDiProvider {
	private lateinit var appComponent: AppComponent
	override fun onCreate() {
		super.onCreate()
		initTimberWithCrashlytics(this)
		appComponent = createDaggerAppComponent()
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}

	override fun provideAppComponent(): AppComponent {
		return appComponent
	}

	private fun createDaggerAppComponent(): AppComponent {
		return DaggerAppComponent.builder()
			.appModule(AppModule(this))
			.build()
	}

}
