package com.med.utilization.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.med.utilization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//	@Inject
//	lateinit var vmFactoryProvider: Provider<MainViewModel.Factory>
//	private val viewModel: MainViewModel by savedStateViewModelWithProvider {
//		vmFactoryProvider.get().create(it)
//	}

	override fun onCreate(savedInstanceState: Bundle?) {
//		DaggerMainComponent.builder()
//			.appComponent(getAppComponent())
//			.build()
//			.inject(this)
		super.onCreate(savedInstanceState)
		val binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		with(binding) {
			val navController = frameContentMain.getNavCtrl(this@MainActivity)
//			if (savedInstanceState == null && navController != null) {
//				btmNavMain.setupWithNavController(navController)
//				btmNavMain.itemIconTintList = null //Can't set on XML because it's not working on the API >= 26

//				navController.navigate(R.id.nav_health_timeline)
//				finish()

//			}

		}

	}


}
