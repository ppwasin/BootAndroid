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
	kapt(Libs.roomKapt)

	implementation(Libs.coroutineCore)
	implementation(Libs.coroutineAndroid)

	compileOnly(Libs.autoService)
	kapt(Libs.autoServiceKapt)

	implementation(Libs.playCore)
}
