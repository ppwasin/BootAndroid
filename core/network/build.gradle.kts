apply<plugin.Junit5Plugin>()
plugins {
    moduleLib()
    kotlinAndroid()
}
android {
    configureAndroidLib()
}

dependencies {
    implementation(Libs.kotlinStd)
    implementation(Libs.appCompat)
    implementation(Libs.coroutineCore)
    addNetwork()
}