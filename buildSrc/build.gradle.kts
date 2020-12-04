plugins {
	`kotlin-dsl`
}

repositories {
	google()
	jcenter()
	gradlePluginPortal()
}

dependencies {
	implementation(BuildPlugins.androidGradle)
	implementation(BuildPlugins.kotlinGradlePlugin)
	implementation("com.android.tools.build:builder:${Versions.androidGradle}")
	implementation("com.android.tools.build:builder-model:${Versions.androidGradle}")
}

kotlin {
	// Add Deps to compilation, so it will become available in main project
	sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}