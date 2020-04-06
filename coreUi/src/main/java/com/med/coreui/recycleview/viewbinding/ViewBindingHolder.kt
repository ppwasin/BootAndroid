package com.med.coreui.recycleview.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.med.coreui.databinding.ItemFallbackBinding
import com.med.coreui.recycleview.ItemDelegateAdapter


class ViewBindingHolder<out T : ViewBinding>(val binding: T) :
	RecyclerView.ViewHolder(binding.root) {
	constructor(
		parent: ViewGroup,
		creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
	) : this(
		creator(LayoutInflater.from(parent.context), parent, false)
	)
}

class ViewBindingAdapter : ItemDelegateAdapter() {

	override fun onCreateFallback(
		parent: ViewGroup,
		viewType: Int
	): ViewBindingHolder<ViewBinding> {
		return ViewBindingHolder(
			parent,
			ItemFallbackBinding::inflate
		)
	}
}


//fun <T : ViewBinding> ViewGroup.viewHolderFrom(
//	creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
//): ViewBindingHolder<T> = ViewBindingHolder(this, creator)

//open class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), LayoutContainer {
//	override val containerView: View? = binding.root
//	companion object {
//		fun create(parent: ViewGroup, layoutId: Int): BindingViewHolder {
//			val binding =
//				DataBindingUtil.inflate<ViewDataBinding>(
//					LayoutInflater.from(parent.context),
//					layoutId,
//					parent,
//					false
//				)
//			return BindingViewHolderImpl(binding)
//		}
//	}
//
//	inline fun <reified T : ViewDataBinding> asApply(block: T.() -> Unit) {
//		(this.binding as? T)?.apply { block(this) }
//	}
//
//	var tag: Any? = null
//
//	fun getContext(): Context = binding.root.context
//
//}