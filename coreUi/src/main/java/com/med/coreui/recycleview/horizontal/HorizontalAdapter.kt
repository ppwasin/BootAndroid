package com.med.coreui.recycleview.horizontal

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView


fun <VH : RecyclerView.ViewHolder> attachHorizontal(
	recyclerView: RecyclerView,
	viewPool: RecyclerView.RecycledViewPool? = null,
//	itemDecoration: RecyclerView.ItemDecoration? = null,
	adapter: RecyclerView.Adapter<VH>
) {
	val context = recyclerView.context
	recyclerView.layoutManager = object :
		LinearLayoutManager(context, HORIZONTAL, false) {
		override fun canScrollVertically(): Boolean {
			return false
		}
	}
	recyclerView.adapter = adapter
//	recyclerView.addItemDecoration(itemDecoration)

	if (viewPool != null)
		recyclerView.setRecycledViewPool(viewPool)

	recyclerView.isNestedScrollingEnabled = false

//		if (recyclerView.onFlingListener == null) {
//			PagerSnapHelper().attachToRecyclerView(recyclerView)
//		}

	val smoothScroller = object : LinearSmoothScroller(context) {
		override fun getVerticalSnapPreference(): Int {
			return SNAP_TO_START
		}
	}

	adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
		override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
			super.onItemRangeInserted(positionStart, itemCount)
			smoothScroller.targetPosition = 0
			recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
		}
	})
}