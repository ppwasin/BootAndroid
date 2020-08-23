package com.med.utilization.bnv.di

import com.med.utilization.bnv.home.HomeFragment
import com.med.utilization.di.AppComponent
import com.med.utilization.di.AssistMainModule
import dagger.Component

@Component(
	dependencies = [AppComponent::class],
	modules = [MockApiModule::class, AssistMainModule::class]
)
@ArticlesScope
interface ArticlesComponent {
	fun inject(fragment: HomeFragment)
}

