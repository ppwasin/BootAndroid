package com.med.feature.entries

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitcompat.SplitCompat
import com.med.coreui.observeNotNull
import com.med.coreui.recycleview.viewbinding.ViewBindingAdapter
import com.med.coreui.savedStateViewModelWithProvider
import com.med.feature.entries.databinding.EntriesBinding
import com.med.feature.entries.di.DaggerEntriesComponent
import com.med.utilization.di.getAppComponent
import javax.inject.Inject
import javax.inject.Provider

class EntriesFragment : Fragment(R.layout.entries) {
	@Inject
	lateinit var vmFactoryProvider: Provider<EntriesViewModel.Factory>
	private val viewModel: EntriesViewModel by savedStateViewModelWithProvider {
		vmFactoryProvider.get().create(it)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = EntriesBinding.bind(view)
		setupRecyclerView(binding)
	}

	private fun setupRecyclerView(binding: EntriesBinding) {
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
		DaggerEntriesComponent.builder()
			.appComponent(context.getAppComponent())
			.build()
			.inject(this)
		super.onAttach(context)
		SplitCompat.installActivity(context)
	}

}