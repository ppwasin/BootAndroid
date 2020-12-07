package plugin

import ext.addTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

private val version = "5.7.0"

class Junit5Plugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType(Test::class.java) {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        project.dependencies.run {
            addTestImplementation(platform("org.junit:junit-bom:$version"))
            addTestImplementation("org.junit.jupiter:junit-jupiter")
        }
    }
}