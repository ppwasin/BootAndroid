pluginManagement {
	repositories {
		google()
		jcenter()
		gradlePluginPortal()
	}
}

includeBuild("plugins")

fun shouldInclude(include: String?, moduleName: String): Boolean {
	if (include != null) {
		val regex = java.util.regex.Pattern.compile(
			include,
			java.util.regex.Pattern.CASE_INSENSITIVE
		)
		return regex.matcher(moduleName).find()
	}
	return true
}

val include: String? by settings

/*
./gradlew app:installBetaDebug -Pinclude="app|dynamicFeature|coreUi|features:featureSearch|features:featureChat|features:featureEntries|features:entrypoint"
*/
//val modules = allModules
//	.filter { shouldInclude(include, it) }
//	.map { ":$it" }
//include(*modules.toTypedArray())
val coreModule = rootDir
	.walk()
	.maxDepth(1)
	.filter {
		it.name != "buildSrc" && it.name != "plugins"
				&& it.isDirectory
				&& file("${it.absolutePath}/build.gradle.kts").exists()
	}
	.map { ":${it.name}" }
val featureModel = File(rootDir, "features")
	.walk()
	.maxDepth(1)
	.filter {
		it.name != "buildSrc" && it.name != "plugins"
				&& it.isDirectory
				&& file("${it.absolutePath}/build.gradle.kts").exists()
	}
	.map { ":features:${it.name}" }


(coreModule + featureModel)
	.filter { shouldInclude(include, it) }
	.forEach { include(it) }

rootProject.name = "BootAndroid"
rootProject.buildFileName = "build.gradle.kts"