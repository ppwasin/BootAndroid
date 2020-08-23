package com.med.utilization.main

import com.med.utilization.di.AppComponent
import com.med.utilization.di.AssistMainModule
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class], modules = [AssistMainModule::class])
interface MainComponent {
	fun inject(ui: MainActivity)
}