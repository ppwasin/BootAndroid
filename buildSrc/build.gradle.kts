plugins {
	`kotlin-dsl`
}
repositories {
	jcenter()
	google()
}
object PluginVersions {
	const val kotlin = "1.3.70"
	const val androidGradle = "3.6.1"
}

object Plugins {
	const val androidGradle = "com.android.tools.build:gradle:${PluginVersions.androidGradle}"
	const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
}

dependencies {
	implementation(Plugins.androidGradle)
	implementation(Plugins.kotlinGradlePlugin)
}