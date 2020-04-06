package com.med.coreui.recycleview

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<Item : DiffItem> : DiffUtil.ItemCallback<Item>() {
	override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
		return oldItem.areItemTheSame(newItem)
	}

	override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
		return oldItem.areContentsTheSame(newItem)
	}

	override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
		return oldItem.getChangePayload(newItem)
	}
}