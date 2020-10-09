import com.android.build.gradle.BaseExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun BaseExtension.configureCompose() {
	buildFeatures.compose = true
	composeOptions {
		kotlinCompilerVersion = Versions.kotlin
		kotlinCompilerExtensionVersion = Versions.compose
	}

//	kotlinOptions {
//		jvmTarget = JavaVersion.VERSION_1_8.toString()
//		freeCompilerArgs = freeCompilerArgs + listOf(
//			"-Xallow-jvm-ir-dependencies",
//			"-Xskip-prerelease-check",
//			"-Xopt-in=kotlin.RequiresOptIn",
//			"-Xopt-in=kotlin.OptIn",
//			"-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
//			"-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi"
//		)
//	}
}


fun DependencyHandlerScope.addComposeDeps() {
	addImplementation("androidx.compose.animation:animation:${Versions.compose}")
	addImplementation("androidx.compose.foundation:foundation:${Versions.compose}")
	addImplementation("androidx.compose.foundation:foundation-layout:${Versions.compose}")
	addImplementation("androidx.compose.material:material:${Versions.compose}")
	addImplementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
	addImplementation("androidx.compose.runtime:runtime:${Versions.compose}")
	addImplementation("androidx.compose.ui:ui:${Versions.compose}")
	addImplementation("androidx.ui:ui-tooling:${Versions.compose}")
	addImplementation("com.github.zsoltk:compose-router:${Versions.composeRouter}")
	addImplementation("dev.chrisbanes.accompanist:accompanist-coil:${Versions.composeAppComponist}")
}