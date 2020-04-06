package com.med.feature.entries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EntriesViewModel : ViewModel() {
	fun getItems(): LiveData<List<EntryDisplayItem>> {
		return MutableLiveData(
			listOf(
				EntryDisplayItem(1),
				EntryDisplayItem(2),
				EntryDisplayItem(3)
			)
		)
	}
}