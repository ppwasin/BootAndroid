plugins {
	moduleDFM()
	kotlinAndroid()
	kotlinExt()
	kotlinKapt()
}

android {
	configureDFM {
		addRoomConfig(projectDir)
	}

	configureCompose()
}

dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(AppModule.APP))

	implementation(Libs.timber)
	implementation(Libs.kotlinStd)
	implementation(Libs.coroutineCore)
	implementation(Libs.coroutineAndroid)
	implementation(Libs.appCompat)
	implementation(Libs.googleMaterial)
	addImplementation(Libs.playCore)
	addComposeDeps()

}