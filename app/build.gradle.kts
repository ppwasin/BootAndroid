plugins {
	moduleApp()
	kotlinAndroid()
	kotlinKapt()
	kotlinExt()
	id("com.github.triplet.play") // https://medium.com/androiddevelopers/speed-up-your-android-dynamic-features-flow-testing-d63986cd716a
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
	configureApp {
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

	dynamicFeatures = mutableSetOf(ModuleDependency.FEATURE_SEARCH)
}

dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(ModuleDependency.LIB_DYNAMIC_FEATURE))
	implementation(Libs.kotlinStd)
	implementation(Libs.appCompat)
	implementation(Libs.fragmentKtx)
	implementation(Libs.androidKtx)
	implementation(Libs.constraintLayout)
	testImplementation(Libs.jUnit)
	androidTestImplementation(Libs.androidTextExt)
	androidTestImplementation(Libs.expresso)
	implementation(Libs.playCore)
	implementation(Libs.rxJava)
	implementation(Libs.rxAndroid)
	implementation(Libs.navDynamicFeature)
}
