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
	implementation(project(ModuleDependency.APP))

	implementation(Libs.timber)
	implementation(Libs.kotlinStd)
	implementation(Libs.coroutineCore)
	implementation(Libs.coroutineAndroid)
	implementation(Libs.appCompat)
	implementation(Libs.googleMaterial)
	addImplementation(Libs.playCore)


	implementation("androidx.compose.animation:animation:${Versions.compose}")
	implementation("androidx.compose.foundation:foundation:${Versions.compose}")
	implementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
	implementation("androidx.compose.material:material:${Versions.compose}")
	implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
	implementation("androidx.compose.runtime:runtime:${Versions.compose}")
	implementation("androidx.compose.ui:ui:${Versions.compose}")
	implementation("androidx.ui:ui-tooling:${Versions.compose}")
	implementation("com.github.zsoltk:compose-router:0.16.0")

}