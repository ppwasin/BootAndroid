package com.med.utilization.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
	@Insert
	fun insert(entities: List<Entry>)

	@Query("SELECT * FROM entry")
	fun getEntries(): Flow<List<Entry>>
}