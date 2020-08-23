package com.med.utilization.bnv.di

import com.med.utilization.bnv.data.ArticleService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object MockApiModule {

	@Provides
	@Reusable
	@MockApiScope
	fun provideRetrofit(
		moshi: Moshi,
		okHttpClient: OkHttpClient
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://5e71e2ea942d92001611a768.mockapi.io/")
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.client(okHttpClient)
			.build()
	}

	@Provides
	@Reusable
	fun provideArticleService(@MockApiScope retrofit: Retrofit) =
		retrofit.create(ArticleService::class.java)
}