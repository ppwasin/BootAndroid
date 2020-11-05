import com.android.build.gradle.BaseExtension

private const val FEATURE_PREFIX = ":features"

enum class AppModule(val prefix: String, val actualName: String) {
    App(prefix = ":", actualName = "app"),
    CoreDynamicFeature(prefix = ":", actualName = "dynamicFeature"),
    CoreUi(prefix = ":", actualName = "coreUi"),
    BaseCompose(prefix = ":base", actualName = "ComposeApp"),
    FeatureSearch(prefix = FEATURE_PREFIX, actualName = "featureSearch"),
    FeatureChat(prefix = FEATURE_PREFIX, actualName = "featureChat"),
    FeatureEntries(prefix = FEATURE_PREFIX, actualName = "entrypoint"),
    FeatureMovie(prefix = FEATURE_PREFIX, actualName = "movie"),
    FeatureProjectMgr(prefix = FEATURE_PREFIX, actualName = "projectMgr");

    val buildGradlePath = "$prefix:$actualName"

    companion object {
        private val allModules = values().map(AppModule::buildGradlePath)
        val dynamicFeatureModules = allModules
            .filter { it.startsWith(FEATURE_PREFIX) }
            .toMutableSet()
    }
}

fun BaseExtension.setModuleVariableOnAllBuildTypes() {
    defaultConfig {
        AppModule.values()
            .forEach { module ->
                val moduleVariable = "AppModule_" + module.name
                val moduleName = module.actualName
                buildConfigField("String", moduleVariable, "\"$moduleName\"")
                resValue("string", moduleVariable, moduleName)
            }
    }
//	buildTypes {
//		buildTypes.forEach { buildType ->
//			AppModule
//				.getAllModuleProperties()
//				.forEach { prop ->
//					val moduleVariable = "AppModule_" + prop.name
//					val moduleName = prop.getter.call().toString().removePrefix(":")
//					buildType.buildConfigField("String", moduleVariable, moduleName)
//					buildType.resValue("String", moduleVariable, moduleName)
//				}
//		}
//	}
}
