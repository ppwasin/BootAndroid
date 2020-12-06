import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import ext.*
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.DependencyHandlerScope
import java.io.File

fun BaseExtension.configureApp(
    id: String,
    extDefaultConfig: DefaultConfig.() -> Unit = {}
) {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = id
        versionCode = 1
        versionName = "1.0"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        extDefaultConfig(this)
    }
    compileOptions()
    configureFavor()
    buildFeatures.viewBinding = true
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

fun BaseExtension.configureAndroidLib(extDefaultConfig: DefaultConfig.() -> Unit = {}) {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        extDefaultConfig(this)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures.viewBinding = true
    compileOptions()
}

fun BaseExtension.configureDFM(extDefaultConfig: DefaultConfig.() -> Unit = {}) {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        extDefaultConfig(this)
    }
    compileOptions()
    configureFavor()
    buildFeatures.viewBinding = true
}

fun BaseExtension.configureFavor() {
    flavorDimensions("settings")
    productFlavors {
        create("beta") {
            versionNameSuffix = "-beta"
            dimension = "settings"
        }
        create("live") {
            versionNameSuffix = "-live"
            dimension = "settings"
        }
    }
}

fun BaseExtension.compileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun DefaultConfig.addRoomConfig(projectDir: File) {
    javaCompileOptions {
        annotationProcessorOptions {
            argument("room.incremental", "true")
            arguments.plusAssign(
                mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            )
        }
    }
}

fun DependencyHandlerScope.addsRxDependencies() {
    addImplementation(Libs.rxJava)
    addImplementation(Libs.rxAndroid)
}

fun DependencyHandlerScope.addDagger() {
    addImplementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    addKapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    addKapt("com.google.dagger:dagger-android-processor:${Versions.dagger}")
    addCompileOnly("com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.daggerAssist}")
    addKapt("com.squareup.inject:assisted-inject-processor-dagger2:${Versions.daggerAssist}")

    addAndroidTestImplementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    addKaptAndroidTest("com.google.dagger:dagger-compiler:${Versions.dagger}")
    addKaptAndroidTest("com.google.dagger:dagger-android-processor:${Versions.dagger}")
}

fun DependencyHandlerScope.addNetwork() {
    addImplementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    addImplementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
    addImplementation("com.squareup.moshi:moshi:${Versions.moshi}")
    addImplementation("com.squareup.moshi:moshi-kotlin:${Versions.moshi}")
    addImplementation("com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}")
    addImplementation("com.facebook.stetho:stetho:${Versions.stetho}")
    addImplementation("com.facebook.stetho:stetho-okhttp3:${Versions.stetho}")
}

fun DependencyHandlerScope.addStetho() {
    addImplementation(Libs.stetho)
    addImplementation(Libs.stethoHttp)
}

fun DependencyHandlerScope.addTimber() {
    addImplementation(Libs.timber)
}

fun DependencyHandlerScope.addDate() {
    addImplementation(Libs.jakeThreetenabp)
    //	testImplementation('org.threeten:threetenbp:1.2.1') {
    //		exclude module: 'com.jakewharton.threetenabp:threetenabp:1.2.1'
    //	}
    //	"testImplementation"(Libs.threetenabp) {
    //		"exclude module:"(Libs.jakeThreetenabp)
    //	}
}

fun DependencyHandlerScope.addCoreFeatureDeps() {
    addImplementation(Libs.kotlinStd)
    addImplementation(Libs.appCompat)
    addImplementation(Libs.constraintLayout)
    addImplementation(Libs.recyclerView)
    addImplementation(Libs.viewModel)
    addImplementation(Libs.lifecycle)
    addImplementation(Libs.liveData)
    addImplementation(Libs.lifecycleProcess)
    addImplementation(Libs.lifecycleCommon)

    addImplementation(Libs.lifecycleReactive)
    addImplementation(Libs.paging)
    addImplementation(Libs.room)
    addImplementation(Libs.roomKtx)
    addKapt(Libs.roomKapt)
    addImplementation(Libs.fragmentKtx)

    addImplementation(Libs.coroutineCore)
    addImplementation(Libs.coroutineAndroid)

    addImplementation(Libs.playCore)
    addCompileOnly(Libs.autoService)
    addKapt(Libs.autoServiceKapt)
    addDagger()
}