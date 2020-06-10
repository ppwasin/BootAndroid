package com.med.utilization.db

import android.content.Context
import androidx.room.Room
import com.med.utilization.di.AppContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DbModule {
	@Provides
	@Singleton
	fun providesAppDatabase(@AppContext appContext: Context): AppDatabase {
		return Room.databaseBuilder(appContext, AppDatabase::class.java, "Utilization")
			.fallbackToDestructiveMigration()
			.build()
	}
}