package com.med.feature.entries

import android.view.ViewGroup
import com.med.coreui.recycleview.ItemDelegate
import com.med.coreui.recycleview.viewbinding.ViewBindingHolder
import com.med.coreui.recycleview.viewbinding.createViewBindingHolder
import com.med.feature.entries.databinding.ItemEntryBinding

data class EntryDisplayItem(val id: Int) : ItemDelegate<ViewBindingHolder<ItemEntryBinding>> {
	override fun onCreateViewHolder(parent: ViewGroup): ViewBindingHolder<ItemEntryBinding> =
		parent.createViewBindingHolder(ItemEntryBinding::inflate)

	override fun getViewType() = R.layout.entries

	override fun areItemTheSame(other: Any): Boolean {
		return other is EntryDisplayItem && other.id == id
	}

	override fun onBindViewHolder(holder: ViewBindingHolder<ItemEntryBinding>) {
		holder.binding.entryTxt.text = id.toString()
	}
}