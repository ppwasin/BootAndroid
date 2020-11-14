pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
}

//includeBuild("plugins")

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
rootProject.name = "BootAndroid"
rootProject.buildFileName = "build.gradle.kts"

/*
./gradlew app:installBetaDebug -Pinclude="app|dynamicFeature|coreUi|features:featureSearch|features:featureEntries|features:entrypoint"
*/
//val modules = allModules
//	.filter { shouldInclude(include, it) }
//	.map { ":$it" }
//include(*modules.toTypedArray())
val coreModule = getModules()
val baseModules = getModules("base")
val featureModule = getModules("features")

val allModules = (coreModule + baseModules + featureModule)
    .filter { shouldInclude(include, it) }
    .toList()

println("All Modules: $allModules")
include(*allModules.toTypedArray())

fun getModules(path: String? = null): Sequence<String> {
    val dir =
        if (path != null) File(rootDir, path)
        else rootDir

    val prefix =
        if (path != null) { name: String -> ":$path:${name}" }
        else { name: String -> ":${name}" }
    return dir
        .walk()
        .maxDepth(1)
        .filter {
            it.name != "buildSrc"
                    && it.name != "plugins"
                    && it.name != rootProject.name
                    && it.name != path
                    && it.isDirectory
                    && (file("${it.absolutePath}/build.gradle.kts").exists() || file("${it.absolutePath}/build.gradle").exists())

        }
        .map { prefix(it.name) }
}