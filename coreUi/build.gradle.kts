plugins {
    moduleLib()
    kotlinAndroid()
}
android {
    configureAndroidLib()
    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    api(Libs.kotlinStd)
    api(Libs.appCompat)
    api(Libs.constraintLayout)
    api(Libs.recyclerView)
    api(Libs.cardView)
    api(Libs.viewModel)
    api(Libs.lifecycle)
    api(Libs.liveData)
    api(Libs.lifecycleProcess)
    api(Libs.lifecycleCommon)
    api(Libs.lifecycleReactive)
    api(Libs.paging)
    api(Libs.room)
    api(Libs.fragmentKtx)

    api(Libs.coroutineCore)
    api(Libs.coroutineAndroid)

	api(Libs.playCore)
	api(Libs.autoService)

    api(Libs.timber)
}
