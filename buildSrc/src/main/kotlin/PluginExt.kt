import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object GradlePluginId {
	const val application = "com.android.application"
	const val dfm = "com.android.dynamic-feature"
	const val androidLib = "com.android.library"
	const val kotlinAndroid = "android"
	const val kotlinExtension = "android.extensions"
	const val kotlinKapt = "kapt"
}

fun PluginDependenciesSpec.moduleApp(): PluginDependencySpec = id(GradlePluginId.application)
fun PluginDependenciesSpec.moduleDFM(): PluginDependencySpec = id(GradlePluginId.dfm)
fun PluginDependenciesSpec.moduleLib(): PluginDependencySpec = id(GradlePluginId.androidLib)

fun PluginDependenciesSpec.kotlinAndroid() = kotlin(GradlePluginId.kotlinAndroid)
fun PluginDependenciesSpec.kotlinKapt() = kotlin(GradlePluginId.kotlinKapt)
fun PluginDependenciesSpec.kotlinExt() = kotlin(GradlePluginId.kotlinExtension)
