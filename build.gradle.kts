import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	DependenciesVersionChecker.plugin(this)
}
buildscript {
	repositories {
		google()
		jcenter()
		maven { setUrl("https://plugins.gradle.org/m2/") }
	}
	dependencies {
		classpath(BuildPlugins.androidGradle)
		classpath(BuildPlugins.kotlinGradlePlugin)
		DependenciesVersionChecker.addClassPath(this)
		classpath(BuildPlugins.playPublisher)

	}
}

allprojects {
	repositories {
		google()
		jcenter()

	}
}

subprojects {
	tasks.withType<Test> {
		maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
	}
	tasks.withType<KotlinCompile>().configureEach {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}

}
fun Project.java(configure: JavaPluginExtension.() -> Unit) =
	extensions.configure(JavaPluginExtension::class.java, configure)

tasks {
	val clean by registering(Delete::class) {
		delete(buildDir)
	}
}

tasks {
	// Gradle versions plugin configuration
	"dependencyUpdates"(com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class) {
		resolutionStrategy {
			componentSelection {
				all {
					// Do not show pre-release version of library in generated dependency report
					val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview")
						.map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
						.any { it.matches(candidate.version) }
					if (rejected) {
						reject("Release candidate")
					}
				}
			}
		}
	}
}