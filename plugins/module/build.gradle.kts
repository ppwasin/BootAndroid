plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
}

repositories {
	google()
	jcenter()
	gradlePluginPortal()
}

gradlePlugin {
	// Add fake plugin, if you don't have any
	plugins.register("class-loader-plugin") {
		id = "class-loader-plugin"
		implementationClass = "com.plugin.AppModuleManagement"
	}
	// Or provide your implemented plugins
}