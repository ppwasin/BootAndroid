package com.med.dynamicfeature.installer

import com.google.android.play.core.splitinstall.SplitInstallSessionState

sealed class InstallDynamicFeatureState {
	object Initializing : InstallDynamicFeatureState()
	data class Download(val progress: Int) : InstallDynamicFeatureState()
	object Installing : InstallDynamicFeatureState()
	object Installed : InstallDynamicFeatureState()
	data class Failed(val state: SplitInstallSessionState) : InstallDynamicFeatureState()
}