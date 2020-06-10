package com.med.utilization.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.med.dynamicfeature.installer.*
import com.med.utilization.feature.SearchFeatureProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow

class MainViewModel @AssistedInject constructor(
	private val featureInstaller: FeatureInstallSyntax,
	@Assisted private val saveStateHandle: SavedStateHandle
) : ViewModel() {
	@AssistedInject.Factory
	interface Factory {
		fun create(saveStateHandle: SavedStateHandle): MainViewModel
	}

	fun instantiateHomeFragment(): Flow<MainFeatureInstallState> {
		return featureInstaller.run {
			val (installCmd, stateFlow) = Feature.Search.install()
			installCmd.start()

			stateFlow
				.subscribeOn(Schedulers.io())
				.filter { it.isInstalled() }
				.map {
					when (it.status()) {
						SplitInstallSessionStatus.PENDING -> MainFeatureInstallState.Loading(0)
						SplitInstallSessionStatus.DOWNLOADING -> MainFeatureInstallState.Loading(it.progress())
						SplitInstallSessionStatus.DOWNLOADED -> MainFeatureInstallState.Loading(1)
						SplitInstallSessionStatus.INSTALLING -> MainFeatureInstallState.Loading(1)
						SplitInstallSessionStatus.INSTALLED -> {
							val fragment = loadClass<SearchFeatureProvider>()?.provideUi()
							if (fragment != null)
								MainFeatureInstallState.Success(fragment)
							else
								MainFeatureInstallState.Fail(Throwable(""))

						}
						else -> MainFeatureInstallState.Fail(Throwable(""))
					}

				}
				.asFlow<MainFeatureInstallState>()

		}
	}
}