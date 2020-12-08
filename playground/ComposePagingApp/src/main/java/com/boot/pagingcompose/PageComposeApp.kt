package com.boot.pagingcompose

import android.app.Application
import timber.log.Timber

class PageComposeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}