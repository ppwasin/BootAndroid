package com.med.feature.entries

import com.med.utilization.db.Entry
import retrofit2.http.GET

interface EntryRemoteDataSource {
	@GET("/entries")
	suspend fun getEntry(): List<Entry>
}
