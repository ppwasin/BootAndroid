package com.med.utilization.di

import com.med.dynamicfeature.installer.FeatureInstallSyntax
import com.med.utilization.db.AppDatabase
import com.med.utilization.db.DbModule
import com.med.utilization.network.NetworkModule
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, DbModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
	fun featureInstaller(): FeatureInstallSyntax
	fun appDatabase(): AppDatabase
}