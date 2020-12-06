package plugin

import Versions
import com.android.build.gradle.BaseExtension
import ext.addAndroidTestImplementation
import ext.addImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class JetpackComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType<KotlinCompile>().configureEach {
            setupCompiler()
        }
        project.extensions.getByType<BaseExtension>().run {
            setupBuildFeature()
        }

        project.dependencies.run {
            addComposeDependencies()
        }
    }
}

private fun KotlinCompile.setupCompiler() {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi"
        )
    }
}

private fun BaseExtension.setupBuildFeature() {
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

//See more in https://foso.github.io/Jetpack-Compose-Playground/general/getting_started/
private fun DependencyHandler.addComposeDependencies() {
    addImplementation("androidx.compose.animation:animation:${Versions.compose}")
    addImplementation("androidx.compose.compiler:compiler:${Versions.compose}")
    addImplementation("androidx.compose.foundation:foundation:${Versions.compose}")
    addImplementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
    addImplementation("androidx.compose.material:material:${Versions.compose}")
    addImplementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    addImplementation("androidx.compose.runtime:runtime:${Versions.compose}")
    addImplementation("androidx.compose.ui:ui:${Versions.compose}")
    addImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    addImplementation("androidx.compose.ui:ui-util:${Versions.compose}")
    addAndroidTestImplementation("androidx.compose.ui:ui-test:${Versions.compose}")
    addImplementation("com.github.zsoltk:compose-router:${Versions.composeRouter}")
    addImplementation("dev.chrisbanes.accompanist:accompanist-coil:${Versions.composeAppComponist}")
    addImplementation("androidx.navigation:navigation-compose:${Versions.composeNav}")
    addImplementation("androidx.paging:paging-compose:${Versions.composePaging}")
}