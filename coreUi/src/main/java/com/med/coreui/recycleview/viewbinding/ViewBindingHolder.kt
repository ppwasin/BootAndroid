package com.med.coreui.recycleview.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


class ViewBindingHolder<out T : ViewBinding>(val binding: T) :
	RecyclerView.ViewHolder(binding.root) {
	constructor(
		parent: ViewGroup,
		creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
	) : this(
		creator(LayoutInflater.from(parent.context), parent, false)
	)
}