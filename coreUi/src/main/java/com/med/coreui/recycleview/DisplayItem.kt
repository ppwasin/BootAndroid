package com.med.coreui.recycleview

import androidx.recyclerview.widget.RecyclerView

interface DisplayItem<VH : RecyclerView.ViewHolder> : ViewTypeDiffItem {
	//invoke only one per viewtype
	fun afterCreateViewHolder(
		parentViewPool: RecyclerView.RecycledViewPool?,
		holder: VH
	) = Unit

	fun onBindViewHolder(holder: VH)
	fun onViewRecycled(holder: VH) = Unit

	fun onViewAttachedToWindow(holder: VH) = Unit
	fun onViewDetachedFromWindow(holder: VH) = Unit
}