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
    implementation(Libs.kotlinStd)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.cardView)
    implementation(Libs.viewModel)
    implementation(Libs.lifecycle)
    implementation(Libs.liveData)
    implementation(Libs.lifecycleProcess)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleReactive)
    implementation(Libs.paging)
    implementation(Libs.room)
    implementation(Libs.fragmentKtx)

    implementation(Libs.coroutineCore)
    implementation(Libs.coroutineAndroid)

    implementation(Libs.playCore)
    implementation(Libs.autoService)

    implementation(Libs.timber)
}
