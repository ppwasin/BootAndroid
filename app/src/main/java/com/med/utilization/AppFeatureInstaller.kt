package com.med.utilization

import android.app.Application
import android.util.Log
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.med.dynamicfeature.installer.Feature
import com.med.dynamicfeature.installer.FeatureInstallSyntax
import com.med.dynamicfeature.installer.InstallCmd
import com.med.dynamicfeature.installer.InstallDynamicFeatureState
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class AppFeatureInstaller(val app: Application) : FeatureInstallSyntax {
	override val splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(app)
	override val flowMap: MutableMap<Feature, Pair<InstallCmd, Flowable<InstallDynamicFeatureState>>> = mutableMapOf()
	override val disposables: CompositeDisposable = CompositeDisposable()
	override fun onError(error: Throwable) {
		error.printStackTrace()
		Log.e("AppFeatureInstaller", error.toString())
	}
}