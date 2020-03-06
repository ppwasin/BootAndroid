package com.med.utilization

import android.app.Application
import timber.log.Timber

fun initTimberWithCrashlytics(app: Application) {
	if (BuildConfig.DEBUG) {
		Timber.plant(Timber.DebugTree())
	}
//	else {
//		Fabric.with(app, Crashlytics())
//		Timber.plant(crashReportingTree())
//	}
}


//private fun crashReportingTree() = object : Timber.Tree() {
//	override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
//		if (priority == Log.ERROR || priority == Log.DEBUG) {
//			Crashlytics.log(priority, tag, message)
//			if (throwable != null) {
//				Crashlytics.logException(throwable)
//			}
//		} else return
//	}
//}