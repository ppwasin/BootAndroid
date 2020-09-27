val allModules = listOf(
	"app", "dynamicFeature", "coreUi",
	"features:featureSearch",
	"features:featureChat",
	"features:featureEntries",
	"features:entrypoint"
)

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
val modules = allModules
	.filter { shouldInclude(include, it) }
	.map { ":$it" }
include(*modules.toTypedArray())

rootProject.name = "BootAndroid"
rootProject.buildFileName = "build.gradle.kts"