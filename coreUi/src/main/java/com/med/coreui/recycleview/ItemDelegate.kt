package com.med.coreui.recycleview

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

interface ItemDelegate<VH> : DiffItem,
	ViewType {
	fun onCreateViewHolder(parent: ViewGroup): VH
	fun onBindViewHolder(holder: VH)
	fun onViewRecycled(holder: VH) = Unit

	fun onViewAttachedToWindow(holder: VH) = Unit
	fun onViewDetachedFromWindow(holder: VH) = Unit
}

typealias LiveItems = LiveData<List<ItemDelegate<out RecyclerView.ViewHolder>>>