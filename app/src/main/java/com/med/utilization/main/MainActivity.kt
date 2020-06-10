package com.med.utilization.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.med.coreui.savedStateViewModelWithProvider
import com.med.utilization.databinding.ActivityMainBinding
import com.med.utilization.di.getAppComponent
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {
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
		val binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		with(binding) {
			val navController = frameContentMain.getNavCtrl(this@MainActivity)
			if (savedInstanceState == null && navController != null) {
				Timber.v("test")
				btmNavMain.setupWithNavController(navController)
//				btmNavMain.itemIconTintList = null //Can't set on XML because it's not working on the API >= 26
			}

		}

	}


}
