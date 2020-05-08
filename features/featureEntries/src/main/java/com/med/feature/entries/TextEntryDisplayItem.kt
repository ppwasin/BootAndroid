package com.med.feature.entries

import android.view.ViewGroup
import com.med.coreui.recycleview.ItemDelegate
import com.med.coreui.recycleview.viewbinding.ViewBindingHolder
import com.med.coreui.recycleview.viewbinding.createViewBindingHolder
import com.med.feature.entries.databinding.ItemEntryBinding

data class TextEntryDisplayItem(val id: Int, val title: String, val subtitle: String) :
	ItemDelegate<ViewBindingHolder<ItemEntryBinding>> {
	override fun onCreateViewHolder(parent: ViewGroup): ViewBindingHolder<ItemEntryBinding> =
		parent.createViewBindingHolder(ItemEntryBinding::inflate)

	override fun getViewType() = R.layout.item_entry

	override fun areItemTheSame(other: Any): Boolean {
		return other is TextEntryDisplayItem && other.id == id
	}

	override fun onBindViewHolder(holder: ViewBindingHolder<ItemEntryBinding>) {
		with(holder.binding) {
			entryTitle.text = title
			entrySubtitle.text = subtitle
		}
	}
}