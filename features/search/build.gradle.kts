plugins {
	moduleDFM()
	kotlinAndroid()
	kotlinKapt()
}

android {
	configureDFM {
		addRoomConfig(projectDir)
	}
}


dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(project(AppModule.App.buildGradlePath))
    implementation(project(AppModule.CoreUi.buildGradlePath))

    addCoreFeatureDeps()
}
