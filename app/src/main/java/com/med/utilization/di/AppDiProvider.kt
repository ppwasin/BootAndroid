package com.med.utilization.di

import android.content.Context

interface AppDiProvider {
	fun provideAppComponent(): AppComponent
}

fun Context.getAppComponent(): AppComponent {
	return (this.applicationContext as AppDiProvider).provideAppComponent()
}