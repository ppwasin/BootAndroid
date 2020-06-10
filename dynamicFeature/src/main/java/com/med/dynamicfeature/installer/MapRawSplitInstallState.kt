package com.med.dynamicfeature.installer

import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

fun mapRawSplitInstallState(rawState: SplitInstallSessionState): InstallDynamicFeatureState? {
	return when (rawState.status()) {
		SplitInstallSessionStatus.PENDING -> InstallDynamicFeatureState.Initializing
		SplitInstallSessionStatus.DOWNLOADING -> InstallDynamicFeatureState.Download(
			rawState.progress()
		)
		SplitInstallSessionStatus.DOWNLOADED -> InstallDynamicFeatureState.Download(
			1
		)
		SplitInstallSessionStatus.INSTALLING -> InstallDynamicFeatureState.Installing
		SplitInstallSessionStatus.INSTALLED -> InstallDynamicFeatureState.Installed
		SplitInstallSessionStatus.FAILED -> InstallDynamicFeatureState.Failed(
			rawState
		)
//		SplitInstallSessionStatus.CANCELING -> callback(InstallDynamicFeatureState.Loading(InstallDynamicFeatureState.MainState.Cancel, state))
//		SplitInstallSessionStatus.CANCELED -> callback(InstallDynamicFeatureState.Installed(InstallDynamicFeatureState.MainState.Cancel, state))
//		SplitInstallSessionStatus.UNKNOWN -> callback(InstallDynamicFeatureState.Unknown(state))
//		SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> callback(InstallDynamicFeatureState.RequireUserConfirmation(state))
		else -> null
	}


}