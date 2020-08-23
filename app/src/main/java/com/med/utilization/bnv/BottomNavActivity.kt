package com.med.utilization.bnv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.med.utilization.R
import com.med.utilization.databinding.BnvActivityMainBinding


class BottomNavActivity : AppCompatActivity() {

	private var currentNavController: LiveData<NavController>? = null
	private lateinit var toolBar: MaterialToolbar
	private lateinit var binding: BnvActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = BnvActivityMainBinding.inflate(layoutInflater)
		toolBar = binding.toolbar
		setSupportActionBar(toolBar)
		if (savedInstanceState == null) {
			setupBottomNavigationBar()
		} // Else, need to wait for onRestoreInstanceState
		setContentView(binding.root)
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		super.onRestoreInstanceState(savedInstanceState)
		// Now that BottomNavigationBar has restored its instance state
		// and its selectedItemId, we can proceed with setting up the
		// BottomNavigationBar with Navigation
		setupBottomNavigationBar()
	}

	/**
	 * Called on first creation and when restoring state.
	 */
	private fun setupBottomNavigationBar() {
		val bottomNavigationView = binding.btmNavMain
		val navGraphIds = listOf(R.navigation.bnv_nav_graph_home, R.navigation.bnv_nav_graph_info)

		// Setup the bottom navigation view with a list of navigation graphs
		val controller = bottomNavigationView.setupWithNavController(
			navGraphIds = navGraphIds,
			fragmentManager = supportFragmentManager,
			containerId = R.id.frame_content_main,
			intent = intent
		)

		// Whenever the selected controller changes, setup the action bar.
		controller.observe(this, { navController ->
			setupActionBarWithNavController(navController)
		})
		currentNavController = controller
	}

	override fun onSupportNavigateUp(): Boolean {
		return currentNavController?.value?.navigateUp() ?: false
	}
}
