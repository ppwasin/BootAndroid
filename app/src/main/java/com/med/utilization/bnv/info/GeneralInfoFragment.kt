package com.med.utilization.bnv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.med.utilization.R
import com.med.utilization.databinding.BnvFragmentGeneralInfoBinding

class GeneralInfoFragment : Fragment() {
	private lateinit var binding: BnvFragmentGeneralInfoBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = BnvFragmentGeneralInfoBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initBindings()
	}

	private fun initBindings() {
		//hides activity's toolbar
		(activity as AppCompatActivity).supportActionBar?.hide()
		with(binding.toolbar) {
			title = "General Info"
		}
//		binding.generalInfoToolbarImage.setImageFromResourcesWithProgressBar(
//			R.drawable.mars_exploration_rover,
//			binding.generalInfoProgress
//		)
		binding.generalInfoProgress.visibility = View.GONE
		binding.generalInfoToolbarImage.setImageResource(R.drawable.mars_exploration_rover)
	}

	override fun onDestroy() {
		(activity as AppCompatActivity).supportActionBar?.show()
		super.onDestroy()
	}
}