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

	buildFeatures.compose = true
	composeOptions {
		kotlinCompilerVersion = Versions.kotlin
		kotlinCompilerExtensionVersion = Versions.compose
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
		freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi"
        )
	}
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