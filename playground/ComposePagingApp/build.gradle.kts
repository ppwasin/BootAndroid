apply<plugin.JetpackComposePlugin>()
apply<plugin.Junit5Plugin>()

plugins {
    moduleApp()
    kotlinAndroid()
}

android {
    configureApp("com.boot.pagingcompose")
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(Libs.timber)
    implementation(Libs.kotlinStd)
    implementation(Libs.coroutineCore)
    implementation(Libs.coroutineAndroid)
    implementation(Libs.appCompat)
    implementation(Libs.googleMaterial)
    implementation(Libs.constraintLayout)
    implementation(Libs.playCore)

    implementation(Libs.navDynamicFeature)
    implementation(Libs.navFragment)
    implementation(Libs.navKtx)

    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.androidTest)
}
