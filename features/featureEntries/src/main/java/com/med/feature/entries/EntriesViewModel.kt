package com.med.feature.entries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.med.coreui.recycleview.LiveItems
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class EntriesViewModel @AssistedInject constructor(
	private val entryRepo: EntryRepository,
	@Assisted private val saveStateHandle: SavedStateHandle
) : ViewModel() {
	fun getItems(): LiveItems {
		return MutableLiveData(
			listOf(
				TextEntryDisplayItem(1, "Title", "Subtitle"),
				TextEntryDisplayItem(2, "Title", "Subtitle"),
				TextEntryDisplayItem(3, "Title", "Subtitle")
			)
		)
	}

	@AssistedInject.Factory
	interface Factory {
		fun create(saveStateHandle: SavedStateHandle): EntriesViewModel
	}
}