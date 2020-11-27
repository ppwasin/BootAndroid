apply<plugin.JetpackComposePlugin>()

plugins {
    moduleApp()
    kotlinAndroid()
}

android {
    configureApp("com.boot.compose")
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName(localSigningConfigs)
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

    dynamicFeatures = mutableSetOf(
        AppModule.FeatureMovie.buildGradlePath,
        AppModule.FeatureProjectMgr.buildGradlePath,
        AppModule.FeatureEntries.buildGradlePath
    )
    setModuleVariableOnAllBuildTypes()
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
    addImplementation(Libs.playCore)

    implementation(Libs.navDynamicFeature)
    implementation(Libs.navFragment)
    implementation(Libs.navKtx)

    //Add it for DFM that use compose
    //https://stackoverflow.com/questions/58979852/dynamic-feature-module-google-services-issue-when-more-than-one-dfm-is-added
    /*
    what it means is your feature 1 as well as feature 2 is bringing the same library
    but your base module i.e app is not having that library so while packaging,
    it doesn't know which version should be taken.
    So to fix it you must add the particular library to your app gradle
    OR
    https://medium.com/pulselive/a-quick-look-at-feature-on-feature-dependencies-in-android-gradle-plugin-4-0-0-5828915d02d3
    * */
//    addComposeDeps()
}
