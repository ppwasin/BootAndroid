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
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)
}

fun DependencyHandlerScope.addDagger() {
    implementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.dagger}")
    compileOnly("com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.daggerAssist}")
    kapt("com.squareup.inject:assisted-inject-processor-dagger2:${Versions.daggerAssist}")

    androidTestImplementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    kaptAndroidTest("com.google.dagger:dagger-compiler:${Versions.dagger}")
    kaptAndroidTest("com.google.dagger:dagger-android-processor:${Versions.dagger}")
}

fun DependencyHandlerScope.addNetwork() {
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
    implementation("com.squareup.moshi:moshi:${Versions.moshi}")
    implementation("com.squareup.moshi:moshi-kotlin:${Versions.moshi}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}")
    implementation("com.facebook.stetho:stetho:${Versions.stetho}")
    implementation("com.facebook.stetho:stetho-okhttp3:${Versions.stetho}")
}

fun DependencyHandlerScope.addStetho() {
    implementation(Libs.stetho)
    implementation(Libs.stethoHttp)
}

fun DependencyHandlerScope.addTimber() {
    implementation(Libs.timber)
}

fun DependencyHandlerScope.addDate() {
    implementation(Libs.jakeThreetenabp)
    //	testImplementation('org.threeten:threetenbp:1.2.1') {
    //		exclude module: 'com.jakewharton.threetenabp:threetenabp:1.2.1'
    //	}
    //	"testImplementation"(Libs.threetenabp) {
    //		"exclude module:"(Libs.jakeThreetenabp)
    //	}
}

fun DependencyHandlerScope.addCoreFeatureDeps() {
    implementation(Libs.kotlinStd)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.viewModel)
    implementation(Libs.lifecycle)
    implementation(Libs.liveData)
    implementation(Libs.lifecycleProcess)
    implementation(Libs.lifecycleCommon)

    implementation(Libs.lifecycleReactive)
    implementation(Libs.paging)
    implementation(Libs.room)
    implementation(Libs.roomKtx)
    kapt(Libs.roomKapt)
    implementation(Libs.fragmentKtx)

    implementation(Libs.coroutineCore)
    implementation(Libs.coroutineAndroid)

    implementation(Libs.playCore)
    compileOnly(Libs.autoService)
    kapt(Libs.autoServiceKapt)
    addDagger()
}