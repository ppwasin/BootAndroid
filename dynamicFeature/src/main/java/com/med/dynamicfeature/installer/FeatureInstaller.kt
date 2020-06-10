package com.med.dynamicfeature.installer

import com.google.android.play.core.splitinstall.SplitInstallSessionState
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

data class InstallCmd(
	val start: () -> Unit,
	val cancel: () -> Unit
)

interface FeatureInstallSyntax : SplitInstallerSyntax {
	val flowMap: MutableMap<Feature, Pair<InstallCmd, Flowable<SplitInstallSessionState>>>
	val disposables: CompositeDisposable
	fun onError(error: Throwable)
	private fun <T : Feature> T.buildInstaller(): Pair<InstallCmd, Flowable<SplitInstallSessionState>> {
		val startRelay = PublishProcessor.create<Unit>()
		val cancelRelay = PublishProcessor.create<Unit>()
		val resultRelay = BehaviorProcessor.create<SplitInstallSessionState>()

		disposables.add(
			startRelay
				.switchMap { installFeature(moduleName).takeUntil(cancelRelay) }
				.subscribe(resultRelay::onNext, ::onError)
		)

		return Pair(
			InstallCmd({ startRelay.onNext(Unit) }, { cancelRelay.onNext(Unit) }),
			resultRelay
		)
	}

	fun <T : Feature> T.install(): Pair<InstallCmd, Flowable<SplitInstallSessionState>> {
		return flowMap.getOrPut(this, { buildInstaller() })
	}
}
