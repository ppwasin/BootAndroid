package com.med.utilization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.med.dynamicfeature.installer.Feature
import com.med.dynamicfeature.installer.isInstallFinish
import com.med.dynamicfeature.installer.loadClass
import com.med.utilization.feature.SearchFeatureProvider
import com.pandora.bottomnavigator.BottomNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
	private val navigator = BottomNavigator.onCreate(
		fragmentContainer = R.id.frame_container,
		bottomNavigationView = findViewById(R.id.bottomnav_view),
		rootFragmentsFactory = mapOf(
//			R.id.nav_home to { RootFragment1() },
////			R.id.tab2 to { RootFragment2() },
////			R.id.tab3 to { RootFragment3() }
		),
		defaultTab = R.id.nav_home,
		activity = this
	)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		(application as AppDiProvider).featureSyntax.run {
			val (installCmd, stateFlow) = Feature.Search.install()
			installCmd.start()
			println("start load feature")

			stateFlow
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ installState ->
					println(installState)
					if (installState.isInstallFinish()) {
						println("install finish")
						loadClass<SearchFeatureProvider>()?.provideUi()?.let { fragment ->
							println("get fragment")
							supportFragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment)
								.commit()
						}
					}
				}, {
					it.printStackTrace()
				})
		}
	}
}
