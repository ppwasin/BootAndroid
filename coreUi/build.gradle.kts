plugins {
    moduleLib()
    kotlinAndroid()
}
android {
    configureAndroidLib()
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Libs.kotlinStd)
    implementation(Libs.fragmentKtx)
    implementation(Libs.viewModel)
}
