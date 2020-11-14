package com.med.feature.search

import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.med.utilization.feature.SearchFeatureProvider

@AutoService(SearchFeatureProvider::class)
class SearchFeatureProviderImpl : SearchFeatureProvider {
	override fun provideUi(): Fragment {
		return SearchFragment()
	}
}