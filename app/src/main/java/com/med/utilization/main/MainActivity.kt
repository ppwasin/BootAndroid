package com.med.utilization.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.med.coreui.savedStateViewModelWithProvider
import com.med.utilization.R
import com.med.utilization.di.getAppComponent
import com.pandora.bottomnavigator.BottomNavigator
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {
	private val navigator by lazy {
		BottomNavigator.onCreate(
			fragmentContainer = R.id.frame_container,
			bottomNavigationView = findViewById(R.id.bottomnav_view),
			rootFragmentsFactory = mapOf(
//			R.id.nav_home to { RootFragment1() },
//			R.id.tab2 to { RootFragment2() },
//			R.id.tab3 to { RootFragment3() }
			),
			defaultTab = R.id.nav_home,
			activity = this
		)
	}
	@Inject
	lateinit var vmFactoryProvider: Provider<MainViewModel.Factory>
	private val viewModel: MainViewModel by savedStateViewModelWithProvider {
		vmFactoryProvider.get().create(it)
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		DaggerMainComponent.builder()
			.appComponent(getAppComponent())
			.build()
			.inject(this)
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_main)
		viewModel.instantiateHomeFragment()

//		(application as AppDiProvider).featureSyntax.run {
//			val (installCmd, stateFlow) = Feature.Search.install()
//			installCmd.start()
//			println("start load feature")
//
//			stateFlow
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe({ installState ->
//					println(installState)
//					if (installState.isInstallFinish()) {
//						println("install finish")
//						loadClass<SearchFeatureProvider>()?.provideUi()?.let { fragment ->
//							println("get fragment")
//							supportFragmentManager.beginTransaction()
//								.replace(R.id.frame_container, fragment)
//								.commit()
//						}
//					}
//				}, {
//					it.printStackTrace()
//				})
//		}
	}
}
