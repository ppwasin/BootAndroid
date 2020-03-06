package com.med.utilization.di

import com.med.dynamicfeature.installer.FeatureInstallSyntax
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
	fun featureInstaller(): FeatureInstallSyntax
}