plugins {
	moduleApp()
	kotlinAndroid()
	kotlinKapt()
	kotlinExt()
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

	dynamicFeatures = AppModule.dynamicFeatureModules
}

dependencies {
//	println(com.plugin.AppPlugin.someString)
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(AppModule.CoreDynamicFeature.buildGradlePath))
	implementation(project(AppModule.CoreUi.buildGradlePath))
	testImplementation(Libs.jUnit)
	androidTestImplementation(Libs.androidTextExt)
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
	addImplementation(Libs.room)
	addImplementation(Libs.roomKtx)
	addKapt(Libs.roomKapt)
	addDate()
	addDagger()
	addNetwork()

	implementation(Libs.appCompat)
	implementation(Libs.constraintLayout)
//	implementation(Libs.activityKtx)
//	implementation(Libs.viewModel)
//	implementation(Libs.lifecycle)
//	implementation(Libs.liveData)
//	implementation(Libs.lifecycleProcess)
//	implementation(Libs.lifecycleCommon)
//	implementation(Libs.bottomNavigator)
//
	implementation(Libs.navDynamicFeature)
	implementation(Libs.navFragment)
	implementation(Libs.navKtx)


	implementation(Libs.googleMaterial)

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
	addComposeDeps()
}
