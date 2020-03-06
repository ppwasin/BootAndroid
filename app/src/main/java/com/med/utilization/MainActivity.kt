package com.med.utilization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.med.dynamicfeature.installer.Feature
import com.med.dynamicfeature.installer.isInstallFinish
import com.med.dynamicfeature.installer.loadClass
import com.med.utilization.feature.SearchFeatureProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
					textView.text = "${textView.text}\n\n load feature result: $installState}"
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
