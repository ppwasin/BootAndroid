package com.med.coreui.recycleview

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