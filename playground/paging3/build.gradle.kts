apply<plugin.Junit5Plugin>()

plugins {
    moduleApp()
    kotlinAndroid()
    id("kotlin-parcelize")
}

android {
    configureApp("com.boot.paging3")
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    Paging3.setup(this)
    implementation(project(AppModule.Core.Network.getProjectPath()))
    implementation(Libs.timber)
    implementation(Libs.kotlinStd)
    implementation(Libs.coroutineCore)
    implementation(Libs.coroutineAndroid)
    implementation(Libs.appCompat)
    implementation(Libs.googleMaterial)
    implementation(Libs.constraintLayout)

    implementation(Libs.navFragment)
    implementation(Libs.navKtx)

    androidTestImplementation(Libs.androidTest)
}
