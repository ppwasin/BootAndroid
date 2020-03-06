import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

/*Usage:
./gradlew dependencyUpdates
 */
object DependenciesVersionChecker {
	private const val version = Versions.dependencyUpdateChecker
	private const val depVersionChecker = "com.github.ben-manes.versions"
	fun plugin(scope: PluginDependenciesSpec) = with(scope) {
		id(depVersionChecker) version(version)
	}

	fun addClassPath(scope: DependencyHandler) {
		scope.apply {
			add(ScriptHandler.CLASSPATH_CONFIGURATION, "com.github.ben-manes:gradle-versions-plugin:$version")
		}
	}
}

