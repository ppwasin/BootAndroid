import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.addImplementation(notation: String) {
	add("implementation", notation)
}

fun DependencyHandlerScope.addAndroidTestImplementation(notation: String) {
	add("androidTestImplementation", notation)
}

fun DependencyHandlerScope.addKapt(notation: String) {
	add("kapt", notation)
}

fun DependencyHandlerScope.addKaptAndroidTest(notation: String) {
	add("kaptAndroidTest", notation)
}