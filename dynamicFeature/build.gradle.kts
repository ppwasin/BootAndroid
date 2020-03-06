plugins {
    moduleLib()
    kotlinAndroid()
    kotlinExt()
    kotlinKapt()
}
android {
    configureAndroidLib()
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Libs.kotlinStd)
    implementation(Libs.appCompat)
    implementation(Libs.coroutineCore)
    implementation(Libs.coroutineAndroid)
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)
    implementation(Libs.playCore)

    implementation(Libs.lifecycleProcess)
    implementation(Libs.lifecycleCommon)
}
