package com.med.utilization.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface EntryDao {
	@Insert
	fun insert(entities: List<Entry>)
}