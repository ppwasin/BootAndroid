object AndroidSdk {
	const val min = 21
	const val compile = 29
	const val target = compile
	const val buildTools = "29.0.3"
}

object BuildPlugins {
	const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
	const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	const val googleService = "com.google.gms:google-services:${Versions.googleService}"
	const val fabric = "io.fabric.tools:gradle:${Versions.fabric}"
	const val aacNav = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidxNav}"
	const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.4.2.0"
	const val playPublisher = "com.github.triplet.gradle:play-publisher:${Versions.playPublisher}"
}

object Libs {
	const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
	const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
	const val googleMaterial = "com.google.android.material:material:${Versions.materialUi}"
	const val androidKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
	const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
	const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
	const val cardView = "androidx.cardview:cardview:1.0.0"
	const val firebaseMsg = "com.google.firebase:firebase-messaging:19.0.0"
	const val playCore = "com.google.android.play:core:${Versions.playCore}"

	//Rx
	const val rxPermission = "com.github.tbruyelle:rxpermissions:0.10.2"
	const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
	const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
	const val rxExt = "com.github.akarnokd:rxjava2-extensions:${Versions.rxExt}"

	//Network
	const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
	const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
	const val moshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"

	//Date
	const val jakeThreetenabp = "com.jakewharton.threetenabp:threetenabp:1.3.0"
//	const val threetenabp = "org.threeten:threetenbp:1.2.1"

	//Debug
	const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
	const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
	const val stethoHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

	//FP
	const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrowkt}"
	const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrowkt}"

	//Testing
	const val mockk = "io.mockk:mockk-android:${Versions.mockk}"

	//https://developer.android.com/jetpack/androidx/releases/room
	const val room = "androidx.room:room-runtime:${Versions.room}"
	const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
	const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
	const val roomKapt = "androidx.room:room-compiler:${Versions.room}"

	//https://developer.android.com/jetpack/androidx/releases/lifecycle
	const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
	const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
	const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
	const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
	const val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
	const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
	const val lifecycleReactive = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}"
	const val archTest = "androidx.arch.core:core-testing:${Versions.androidArch}"
	const val livedatTest = "com.jraska.livedata:testing-ktx:1.1.0"
	const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

	//https://developer.android.com/jetpack/androidx/releases/navigation
	const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNav}"
	const val navKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidxNav}"
	const val bottomNavigator = "com.pandora.bottomnavigator:bottom-navigator:${Versions.bottomNavigator}"

	//https://developer.android.com/guide/navigation/navigation-dynamic
	const val navDynamicFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.androidxNav}"

	//https://developer.android.com/jetpack/androidx/releases/fragment
	const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
	const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"


	//Coroutine
	const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
	const val coroutineAndroid =
		"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
	const val coroutineRx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.coroutine}"
	const val coroutineReactive =
		"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutine}"

	//AutoService
	const val autoService =
		"com.google.auto.service:auto-service-annotations:${Versions.autoService}"
	const val autoServiceKapt = "com.google.auto.service:auto-service:${Versions.autoService}"

	//Testing
	const val jUnit = "junit:junit:${Versions.junit}"
	const val androidTest = "androidx.test.ext:junit:${Versions.androidJunit}"
	const val expresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

}

object Arrowkt {
	private const val version = "0.11.0"
	const val core = "io.arrow-kt:arrow-core:$version"
	const val syntax = "io.arrow-kt:arrow-syntax:$version"
}

object Paging3 {
	private const val version = "3.0.0-alpha12"
	const val runtime = "androidx.paging:paging-runtime:$version"
	const val common = "androidx.paging:paging-common:$version" // alternatively - without Android dependencies for test
}