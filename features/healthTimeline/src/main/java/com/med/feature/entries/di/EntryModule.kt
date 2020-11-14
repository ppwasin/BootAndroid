package com.med.feature.entries.di

import com.med.utilization.db.AppDatabase
import com.med.utilization.db.EntryDao
import dagger.Module
import dagger.Provides

@Module
object EntryModule {

	@Provides
	fun provideMsgDao(appDatabase: AppDatabase): EntryDao {
		return appDatabase.entryDao()
	}
}