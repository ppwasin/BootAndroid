package com.med.dynamicfeature.installer

sealed class FeatureInstallState<T : Feature> {
	class Loading<T : Feature> : FeatureInstallState<T>()
	class Success<T : Feature>(val feature: T) : FeatureInstallState<T>()
	class Error<T : Feature>(val error: Throwable) : FeatureInstallState<T>()
}