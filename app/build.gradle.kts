plugins {
	moduleApp()
	kotlinAndroid()
	kotlinKapt()
	gradlePlay()
//	id("class-loader-plugin")
}
play {
	serviceAccountCredentials = rootProject.file("release-app.json")
	defaultToAppBundles = true
	artifactDir = file("build/outputs/bundle/betaDebug")
}
afterEvaluate {
	tasks.named("uploadBetaReleasePrivateBundle").configure {
		dependsOn("bundleBetaDebug")
	}
}
android {
	configureApp("com.med.utilization") {
		addRoomConfig(projectDir)
	}
	setupLocalSign()
	buildTypes {
		getByName("release") {
			signingConfig = signingConfigs.getByName(localSigningConfigs) //TODO move to FastFile
			isMinifyEnabled = true
			proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
		}
	}

	dynamicFeatures = mutableSetOf(
		AppModule.FeatureHealthTimeline.buildGradlePath,
		AppModule.FeatureSearch.buildGradlePath
	)
	setModuleVariableOnAllBuildTypes()
}

dependencies {
//	println(com.plugin.AppPlugin.someString)
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(AppModule.CoreDynamicFeature.buildGradlePath))
	implementation(project(AppModule.CoreUi.buildGradlePath))
	testImplementation(Libs.jUnit)
	androidTestImplementation(Libs.androidTest)
	androidTestImplementation(Libs.expresso)
	implementation(Libs.kotlinStd)
	implementation(Libs.playCore)
	implementation(Libs.rxJava)
	implementation(Libs.rxAndroid)
	implementation(Libs.coroutineCore)
	implementation(Libs.coroutineAndroid)
	implementation(Libs.coroutineRx)
	implementation(Libs.coroutineReactive)

	implementation(Libs.timber)
	implementation(Libs.room)
	implementation(Libs.roomKtx)
	kapt(Libs.roomKapt)
	addDate()
	addDagger()
	addNetwork()

	implementation(Libs.appCompat)
	implementation(Libs.constraintLayout)
	implementation(Libs.navDynamicFeature)
	implementation(Libs.navFragment)
	implementation(Libs.navKtx)


	implementation(Libs.googleMaterial)

}
