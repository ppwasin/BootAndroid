package com.med.feature.entries

import com.med.utilization.db.Entry
import com.med.utilization.db.EntryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntryRepository @Inject constructor(
	private val entryDao: EntryDao
) {
	fun getEntryData(): Flow<List<Entry>> {
		TODO()
	}
}