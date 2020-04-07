package com.med.coreui.recycleview

import android.view.ViewGroup

interface ItemDelegate<VH> : DiffItem,
	ViewType {
	fun onCreateViewHolder(parent: ViewGroup): VH
	fun onBindViewHolder(holder: VH)
	fun onViewRecycled(holder: VH) = Unit

	fun onViewAttachedToWindow(holder: VH) = Unit
	fun onViewDetachedFromWindow(holder: VH) = Unit
}