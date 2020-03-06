import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.DependencyHandlerScope
import java.io.File


fun BaseExtension.configureApp(extDefaultConfig: DefaultConfig.() -> Unit = {}) {
	compileSdkVersion(AndroidSdk.compile)
	defaultConfig {
		applicationId = "com.med.utilization"
		versionCode = 1
		versionName = "1.0"
		minSdkVersion(AndroidSdk.min)
		targetSdkVersion(AndroidSdk.target)
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		extDefaultConfig(this)
	}
	compileOptions()
	configureFavor()
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
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
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
			arguments = mapOf(
				"room.schemaLocation" to "$projectDir/schemas",
				"room.incremental" to "true",
				"room.expandProjection" to "true"
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

	addAndroidTestImplementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
	addKaptAndroidTest("com.google.dagger:dagger-compiler:${Versions.dagger}")
	addKaptAndroidTest("com.google.dagger:dagger-android-processor:${Versions.dagger}")
}

fun DependencyHandlerScope.addNetwork() {
	addImplementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
	addImplementation("com.squareup.retrofit2:converter-moshi:${Versions.retrofit}")
	addImplementation("com.squareup.moshi:moshi:1.8.0")
	addImplementation("com.squareup.moshi:moshi-kotlin:1.6.0")
	addImplementation("com.squareup.okhttp3:logging-interceptor:4.2.1")
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
	"testImplementation"(Libs.threetenabp) {
		"exclude module:"(Libs.jakeThreetenabp)
	}
}


fun DependencyHandlerScope.addCoreFeatureDeps() {
	addImplementation(Libs.kotlinStd)

	addImplementation(Libs.appCompat)
	addImplementation(Libs.constraintLayout)
	addImplementation(Libs.recyclerView)
	addImplementation(Libs.cardView)

	addImplementation(Libs.viewModel)
	addImplementation(Libs.lifecycle)
	addImplementation(Libs.liveData)
	addImplementation(Libs.lifecycleProcess)
	addImplementation(Libs.lifecycleCommon)
	addImplementation(Libs.lifecycleReactive)
	addImplementation(Libs.paging)
	addImplementation(Libs.room)
	addImplementation(Libs.fragmentKtx)
	addKapt(Libs.roomKapt)

	addImplementation(Libs.coroutineCore)
	addImplementation(Libs.coroutineAndroid)

	addCompileOnly(Libs.autoService)
	addKapt(Libs.autoServiceKapt)

	addImplementation(Libs.playCore)
}