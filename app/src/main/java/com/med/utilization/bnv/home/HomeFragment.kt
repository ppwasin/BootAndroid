package com.med.utilization.bnv.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.med.coreui.recycleview.viewbinding.ViewBindingAdapter
import com.med.coreui.savedStateViewModelWithProvider
import com.med.utilization.R
import com.med.utilization.bnv.di.DaggerArticlesComponent
import com.med.utilization.databinding.BnvFragmentHomeBinding
import com.med.utilization.di.getAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class HomeFragment : Fragment(R.layout.bnv_fragment_home) {
	@Inject
	lateinit var vmFactoryProvider: Provider<HomeViewModel.Factory>
	private val viewModel: HomeViewModel by savedStateViewModelWithProvider {
		vmFactoryProvider.get().create(it)
	}
	private val adapter by lazy { ViewBindingAdapter() }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = BnvFragmentHomeBinding.bind(view)
		setupRecyclerView(binding.rv, viewModel.getContent())
		viewModel.fetch()
	}

	private fun setupRecyclerView(rv: RecyclerView, items: Flow<List<ArticleItem>>) {
		rv.adapter = adapter
		rv.layoutManager = LinearLayoutManager(context)
		viewLifecycleOwner.lifecycleScope.launch {
			items.collect(adapter::submitListT)
		}
	}

	override fun onAttach(context: Context) {
		DaggerArticlesComponent.builder()
			.appComponent(context.getAppComponent())
			.build()
			.inject(this)
		super.onAttach(context)
	}
}