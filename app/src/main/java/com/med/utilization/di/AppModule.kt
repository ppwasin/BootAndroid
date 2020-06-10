package com.med.utilization.di

import android.app.Application
import android.content.Context
import com.med.dynamicfeature.installer.FeatureInstallSyntax
import com.med.utilization.feature.AppFeatureInstaller
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
	@Provides
	@AppContext
	fun getApplication(): Context {
		return app
	}

	@Provides
	@Singleton
	fun provideFeatureInstaller(): FeatureInstallSyntax {
		return AppFeatureInstaller(app)
	}
}