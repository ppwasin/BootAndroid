package com.med.feature.entries

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitcompat.SplitCompat
import com.med.coreui.observeNotNull
import com.med.coreui.recycleview.viewbinding.ViewBindingAdapter
import com.med.feature.entries.databinding.EntriesBinding

class EntriesFragment : Fragment(R.layout.entries) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = EntriesBinding.bind(view)
		setupRecyclerView(binding, EntriesViewModel())
	}

	private fun setupRecyclerView(binding: EntriesBinding, viewModel: EntriesViewModel) {
		val adapter = ViewBindingAdapter()
		with(binding.entryRv) {
			this.adapter = adapter
			layoutManager = LinearLayoutManager(context)
		}

		viewModel.getItems().observeNotNull(this) {
			adapter.submitListT(it)
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		SplitCompat.installActivity(context)
	}

}