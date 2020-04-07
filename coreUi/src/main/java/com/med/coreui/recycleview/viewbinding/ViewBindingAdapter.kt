package com.med.coreui.recycleview.viewbinding

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.med.coreui.databinding.ItemFallbackBinding
import com.med.coreui.recycleview.ItemDelegateAdapter

class ViewBindingAdapter : ItemDelegateAdapter() {

	override fun onCreateItemFallback(
		parent: ViewGroup,
		viewType: Int
	): ViewBindingHolder<ViewBinding> {
		return ViewBindingHolder(
			parent,
			ItemFallbackBinding::inflate
		)
	}
}