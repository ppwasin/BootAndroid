package com.med.utilization.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.med.dynamicfeature.installer.FeatureInstallSyntax
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class MainViewModel @AssistedInject constructor(
	private val featureInstaller: FeatureInstallSyntax,
	@Assisted private val saveStateHandle: SavedStateHandle
) : ViewModel() {
	@AssistedInject.Factory
	interface Factory {
		fun create(saveStateHandle: SavedStateHandle): MainViewModel
	}

	fun instantiateHomeFragment() {
		Timber.v("test")
	}
}