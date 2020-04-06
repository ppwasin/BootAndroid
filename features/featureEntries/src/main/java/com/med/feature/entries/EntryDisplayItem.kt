package com.med.feature.entries

import android.view.ViewGroup
import com.med.coreui.recycleview.ItemDelegate
import com.med.coreui.recycleview.viewbinding.ViewBindingHolder
import com.med.feature.entries.databinding.ItemEntryBinding

data class EntryDisplayItem(val id: Int) : ItemDelegate<ViewBindingHolder<ItemEntryBinding>> {
	override fun onCreateViewHolder(parent: ViewGroup): ViewBindingHolder<ItemEntryBinding> {
		return ViewBindingHolder(
			parent,
			ItemEntryBinding::inflate
		)
	}

	override fun getViewType(): Int {
		return R.layout.entries
	}

	override fun areItemTheSame(other: Any): Boolean {
		return other is EntryDisplayItem && other.id == id
	}

	override fun onBindViewHolder(holder: ViewBindingHolder<ItemEntryBinding>) {
		holder.binding.entryTxt.text = id.toString()
	}
}