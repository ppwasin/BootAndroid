package com.med.dynamicfeature.installer

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

data class InstallCmd(
	val start: () -> Unit,
	val cancel: () -> Unit
)

interface FeatureInstallSyntax : SplitInstallerSyntax {
	val flowMap: MutableMap<Feature, Pair<InstallCmd, Flowable<InstallDynamicFeatureState>>>
	val disposables: CompositeDisposable
	fun onError(error: Throwable)
	private fun <T : Feature> T.buildInstaller(): Pair<InstallCmd, Flowable<InstallDynamicFeatureState>> {
		val startRelay = PublishProcessor.create<Unit>()
		val cancelRelay = PublishProcessor.create<Unit>()
		val resultRelay = BehaviorProcessor.create<InstallDynamicFeatureState>()

		disposables.add(
			startRelay
				.switchMap { installFeature(moduleName).takeUntil(cancelRelay) }
				.map(::mapRawSplitInstallState)
				.subscribe(resultRelay::onNext, ::onError)
		)

		return Pair(
			InstallCmd({ startRelay.onNext(Unit) }, { cancelRelay.onNext(Unit) }),
			resultRelay
		)
	}

	fun <T : Feature> T.install(): Pair<InstallCmd, Flowable<InstallDynamicFeatureState>> {
		return flowMap.getOrPut(this, { buildInstaller() })
	}
}
