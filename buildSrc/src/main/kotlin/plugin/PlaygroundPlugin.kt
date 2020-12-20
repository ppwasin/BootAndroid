package plugin

import GradlePluginId
import Libs
import com.android.build.gradle.BaseExtension
import configureApp
import ext.androidTestImplementation
import ext.implementation
import ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import kotlin.properties.Delegates

class PlaygroundPlugin : Plugin<Project> {
    private val Project.option: PlaygroundPluginExtension
        get() = extensions.create("playground")
    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an android module $name")

    override fun apply(project: Project) {
        val extension = project.option

        extension.onAppIdChanged = { id ->
            project.run {
                applyAppPlugins()
                applyPlaygroundPlugins()
                try {
                    val appId = id ?: throw Error("App id is not specify from module $name")
                    androidConfig(appId)
                } finally {
                    extension.onAppIdChanged = null
                }
                addDependencies()
            }

        }
    }


    private fun Project.applyPlaygroundPlugins() {
        plugins.run {
            apply(JetpackComposePlugin::class.java)
            apply(Junit5Plugin::class.java)
        }
    }

    private fun Project.applyAppPlugins() {
        plugins.run {
            apply(GradlePluginId.application)
            apply(GradlePluginId.kotlinAndroid)
        }
    }

    private fun Project.androidConfig(appId: String) {
        android.run {
            configureApp(appId)
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.txt"
                    )
                }
            }
        }
    }

    private fun Project.addDependencies() {
        dependencies.run {
            implementation(Libs.timber)
            implementation(Libs.kotlinStd)
            implementation(Libs.coroutineCore)
            implementation(Libs.coroutineAndroid)
            implementation(Libs.appCompat)
            implementation(Libs.googleMaterial)
            implementation(Libs.constraintLayout)
            implementation(Libs.playCore)

            implementation(Libs.navDynamicFeature)
            implementation(Libs.navFragment)
            implementation(Libs.navKtx)

            testImplementation(Libs.jUnit)
            androidTestImplementation(Libs.androidTest)
        }

    }

}

open class PlaygroundPluginExtension {
    var appId: String? by Delegates.observable(null) { _, _, newValue ->
        onAppIdChanged?.invoke(newValue)
    }

    var onAppIdChanged: ((String?) -> Any)? = null
}