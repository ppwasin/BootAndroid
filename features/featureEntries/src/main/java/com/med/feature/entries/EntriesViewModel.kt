package com.med.feature.entries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.med.coreui.recycleview.LiveItems
import com.med.utilization.db.EntryDao
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class EntriesViewModel @AssistedInject constructor(
	private val entryDao: EntryDao,
	@Assisted private val saveStateHandle: SavedStateHandle
) : ViewModel() {
	fun getItems(): LiveItems {
		return MutableLiveData(
			listOf(
				EntryDisplayItem(1),
				EntryDisplayItem(2),
				EntryDisplayItem(3)
			)
		)
	}

	@AssistedInject.Factory
	interface Factory {
		fun create(saveStateHandle: SavedStateHandle): EntriesViewModel
	}
}