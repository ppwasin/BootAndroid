plugins {
	`kotlin-dsl`
}
//val android_gradle_version: String by project
//val kotlin_version: String by project

repositories {
	google()
	jcenter()
}

dependencies {
	implementation("com.android.tools.build:gradle:4.2.0-alpha07")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0-rc")
}