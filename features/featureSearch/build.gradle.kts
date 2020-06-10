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
}


dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(ModuleDependency.APP))
	implementation(project(ModuleDependency.LIB_CORE_UI))

	addCoreFeatureDeps()
}
