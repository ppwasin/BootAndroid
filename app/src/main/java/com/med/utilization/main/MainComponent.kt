package com.med.utilization.main

import com.med.utilization.di.AppComponent
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainViewModelModule::class])
interface MainComponent {
	fun inject(ui: MainActivity)
}