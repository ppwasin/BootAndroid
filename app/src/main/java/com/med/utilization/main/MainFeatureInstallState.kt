package com.med.utilization.main

import androidx.fragment.app.Fragment


sealed class MainFeatureInstallState {
	data class Loading(val progress: Int) : MainFeatureInstallState()
	data class Success(val fragment: Fragment) : MainFeatureInstallState()
	data class Fail(val error: Throwable) : MainFeatureInstallState()
}