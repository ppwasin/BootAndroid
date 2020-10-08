package com.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AppModuleManagement : Plugin<Project> {
	override fun apply(target: Project) {
		// no-op
	}
	
}

object AppPlugin {
	val someString = "Hello sdf ksdfj lskdfjlsdk jfsldk fj"
}