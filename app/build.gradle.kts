plugins {
	moduleApp()
	kotlinAndroid()
	kotlinKapt()
	kotlinExt()
	gradlePlay()
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

	dynamicFeatures = mutableSetOf(ModuleDependency.FEATURE_SEARCH, ModuleDependency.FEATURE_CHAT)
}

dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(ModuleDependency.LIB_DYNAMIC_FEATURE))
	implementation(project(ModuleDependency.LIB_CORE_UI))
	testImplementation(Libs.jUnit)
	androidTestImplementation(Libs.androidTextExt)
	androidTestImplementation(Libs.expresso)
	implementation(Libs.playCore)
	implementation(Libs.rxJava)
	implementation(Libs.rxAndroid)
	implementation(Libs.navDynamicFeature)
	implementation(Libs.bottomNavigator)
	implementation(Libs.timber)
	addDagger()
}
