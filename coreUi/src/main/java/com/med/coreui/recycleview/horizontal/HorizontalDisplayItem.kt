package com.med.coreui.recycleview.horizontal

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.med.coreui.R
import com.med.coreui.databinding.HorizontalListviewBinding
import com.med.coreui.recycleview.ItemDelegate
import com.med.coreui.recycleview.ItemDelegateAdapter
import com.med.coreui.recycleview.viewbinding.ViewBindingHolder

interface HorizontalDisplayItem : ItemDelegate<ViewBindingHolder<HorizontalListviewBinding>> {
	val items: List<ItemDelegate<RecyclerView.ViewHolder>>
	val horizontalAdapter: ItemDelegateAdapter

	override fun onCreateViewHolder(parent: ViewGroup): ViewBindingHolder<HorizontalListviewBinding> {
		val viewHolder = ViewBindingHolder(parent, HorizontalListviewBinding::inflate)
		val rv = viewHolder.binding.rvHorizontal
		attachHorizontal(rv, rv.recycledViewPool, horizontalAdapter)

		return viewHolder
	}


	override fun onBindViewHolder(holder: ViewBindingHolder<HorizontalListviewBinding>) {
		horizontalAdapter.submitListT(items)
	}

	override fun getViewType(): Int {
		return R.layout.horizontal_listview
	}
}