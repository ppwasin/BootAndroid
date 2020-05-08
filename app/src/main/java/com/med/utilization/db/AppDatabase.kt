package com.med.utilization.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.med.utilization.db.converter.DateTimeConverter

@Database(
	entities = [Entry::class],
	version = 1
)
@TypeConverters(
	DateTimeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun entryDao(): EntryDao
}