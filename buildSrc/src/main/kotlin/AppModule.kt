import com.android.build.gradle.BaseExtension

private const val FEATURE_PREFIX = ":features"

interface HasGradlePath {
    val prefix: String
    val actualName: String
    fun getProjectPath() = "$prefix:$actualName"
}

enum class AppModule(val prefix: String, val actualName: String) {
    CoreDynamicFeature(prefix = ":", actualName = "dynamicFeature"),
    CoreUi(prefix = ":", actualName = "coreUi"),

    App(prefix = ":", actualName = "app"),
    FeatureHealthTimeline(
        prefix = FEATURE_PREFIX,
        actualName = "healthTimeline"
    ), //#XML #Dagger #AssistedInject #DFM
    FeatureSearch(
        prefix = FEATURE_PREFIX,
        actualName = "search"
    ), //#XML #Dagger #AssistedInject #DFM

    BaseCompose(prefix = ":base", actualName = "ComposeApp"), //#Compose
    FeatureEntries(
        prefix = FEATURE_PREFIX,
        actualName = "entrypoint"
    ), //#Compose 1st try - TODO use jetpack nav
    FeatureMovie(prefix = FEATURE_PREFIX, actualName = "movie"), //#Compose #Reyland - InProgress
    FeatureProjectMgr(
        prefix = FEATURE_PREFIX,
        actualName = "projectMgr"
    ); //#Compose #Reyland - InProgress

    val buildGradlePath = "$prefix:$actualName"

    companion object {
        private val allModules = values().map(AppModule::buildGradlePath)
        val dynamicFeatureModules = allModules
            .filter { it.startsWith(FEATURE_PREFIX) }
            .toMutableSet()
    }

    enum class Core : HasGradlePath {

        Network {
            override val actualName: String = "network"
        };

        override val prefix: String = ":core"
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
}
