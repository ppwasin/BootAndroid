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


fun SplitInstallSessionState.isInstalled(): Boolean {
	return status() == SplitInstallSessionStatus.INSTALLED
}