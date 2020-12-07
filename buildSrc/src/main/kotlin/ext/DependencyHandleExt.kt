package ext
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addImplementation(notation: String) {
    add("implementation", notation)
}

fun DependencyHandler.addAndroidTestImplementation(notation: String) {
    add("androidTestImplementation", notation)
}

fun DependencyHandler.addTestImplementation(notation: String) {
    add("testImplementation", notation)
}

fun DependencyHandler.addTestImplementation(notation: Dependency) {
    add("testImplementation", notation)
}

fun DependencyHandler.addKapt(notation: String) {
    add("kapt", notation)
}

fun DependencyHandler.addKaptAndroidTest(notation: String) {
    add("kaptAndroidTest", notation)
}

fun DependencyHandler.addCompileOnly(notation: String) {
	add("compileOnly", notation)
}