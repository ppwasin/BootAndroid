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
	packagingOptions {
		exclude("META-INF/atomicfu.kotlin_module")
	}

	aaptOptions {
		noCompress("filamat", "ktx", "glb")
	}
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
		freeCompilerArgs = listOf(
			"-Xallow-jvm-ir-dependencies",
			"-Xskip-prerelease-check",
			"-Xskip-metadata-version-check"
		)
	}
}

dependencies {
	implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
	implementation(project(ModuleDependency.APP))
//	implementation(Libs.kotlinStd)
//	implementation(Libs.appCompat)
//	implementation(Libs.androidKtx)
//	implementation(Libs.googleMaterial)
//
//	implementation(Libs.coroutineCore)
//	implementation(Libs.coroutineAndroid)
//
//	implementation(Libs.room)
//	implementation(Libs.roomKtx)
//	kapt(Libs.roomKapt)
//
//	implementation(Libs.activityKtx)
//	implementation(Libs.viewModel)
//	implementation(Libs.lifecycle)
//	implementation(Libs.liveData)
//	implementation(Libs.lifecycleProcess)
//	implementation(Libs.lifecycleCommon)
//	addCoreFeatureDeps()

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
	implementation("androidx.compose.runtime:runtime-livedata:${Versions.compose}")
	implementation("androidx.compose.ui:ui:${Versions.compose}")
	implementation("androidx.ui:ui-tooling:${Versions.compose}")
}