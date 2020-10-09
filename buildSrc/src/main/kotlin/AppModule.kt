import kotlin.reflect.full.memberProperties

//import kotlin.reflect.full.memberProperties
private const val FEATURE_PREFIX = ":features"

// "Module" means "project" in terminology of Gradle API. To be specific each "Android module" is a Gradle "subproject"
@Suppress("unused")
object AppModule {
	// All consts are accessed via reflection
	const val APP = ":app"
	const val LIB_DYNAMIC_FEATURE = ":dynamicFeature"
	const val LIB_CORE_UI = ":coreUi"
	const val CORE_COMPOSE = ":base:coreCompose"
	const val FEATURE_SEARCH = ":features:featureSearch"
	const val FEATURE_CHAT = ":features:featureChat"
	const val FEATURE_ENTRIES = ":features:featureEntries"
	const val FEATURE_ENTRYPOINT = ":features:entrypoint"
	const val FEATURE_MOVIE = ":features:movie"


	// False positive" function can be private"
	// See: https://youtrack.jetbrains.com/issue/KT-33610
	fun getAllModules() = AppModule::class.memberProperties
		.filter { it.isConst }
		.map { it.getter.call().toString() }
		.toSet()

	fun getDynamicFeatureModules() = getAllModules()
		.filter { it.startsWith(FEATURE_PREFIX) }
		.toMutableSet()
}
