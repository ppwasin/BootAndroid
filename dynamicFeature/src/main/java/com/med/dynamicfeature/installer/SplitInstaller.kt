package com.med.dynamicfeature.installer

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import kotlin.math.roundToInt

interface SplitInstallerSyntax {
	val splitInstallManager: SplitInstallManager
}

fun SplitInstallerSyntax.isFeatureInstalled(feature: Feature): Boolean {
	return splitInstallManager.installedModules.contains(feature.moduleName)
}

fun SplitInstallerSyntax.installFeature(moduleName: String): Flowable<SplitInstallSessionState> {
//	val splitInstallManager = SplitInstallManagerFactory.create(context)
	return Flowable.create<SplitInstallSessionState>({ emitter ->
		val listener = SplitInstallStateUpdatedListener(emitter::onNext)
		val request = SplitInstallRequest.newBuilder()
			.addModule(moduleName)
			.build()

		var sessionId: Int? = null
		splitInstallManager.startInstall(request)
			.addOnSuccessListener { sessionId = it }
			.addOnFailureListener { emitter.onError(it) }

		splitInstallManager.registerListener(listener)
		emitter.setCancellable {
			val currentSessionId = sessionId
			if (currentSessionId != null) splitInstallManager.cancelInstall(currentSessionId)
			splitInstallManager.unregisterListener(listener)
		}
	}, BackpressureStrategy.LATEST)
}


fun SplitInstallSessionState.progress(): Int {
	return try {
		((bytesDownloaded() / totalBytesToDownload().toFloat()) * 100).roundToInt()
	} catch (ex: Exception) {
		0
	}
}

fun InstallDynamicFeatureState.isInstallFinish(): Boolean {
	return this == InstallDynamicFeatureState.Installed
}

fun mapRawSplitInstallState(rawState: SplitInstallSessionState): InstallDynamicFeatureState? {
	return when (rawState.status()) {
		SplitInstallSessionStatus.PENDING -> InstallDynamicFeatureState.Initializing
		SplitInstallSessionStatus.DOWNLOADING -> InstallDynamicFeatureState.Download(rawState.progress())
		SplitInstallSessionStatus.DOWNLOADED -> InstallDynamicFeatureState.Download(1)
		SplitInstallSessionStatus.INSTALLING -> InstallDynamicFeatureState.Installing
		SplitInstallSessionStatus.INSTALLED -> InstallDynamicFeatureState.Installed
		SplitInstallSessionStatus.FAILED -> InstallDynamicFeatureState.Failed(rawState)
//		SplitInstallSessionStatus.CANCELING -> callback(InstallDynamicFeatureState.Loading(InstallDynamicFeatureState.MainState.Cancel, state))
//		SplitInstallSessionStatus.CANCELED -> callback(InstallDynamicFeatureState.Installed(InstallDynamicFeatureState.MainState.Cancel, state))
//		SplitInstallSessionStatus.UNKNOWN -> callback(InstallDynamicFeatureState.Unknown(state))
//		SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> callback(InstallDynamicFeatureState.RequireUserConfirmation(state))
		else -> null
	}


}

