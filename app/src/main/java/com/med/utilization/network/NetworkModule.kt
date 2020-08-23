package com.med.utilization.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.med.utilization.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {
	private const val NET_TIMEOUT = 10L

	@Provides
	@Reusable
	fun provideLoggingInterceptor(): HttpLoggingInterceptor {
		val logging = HttpLoggingInterceptor()
		logging.level = if (BuildConfig.DEBUG) {
			HttpLoggingInterceptor.Level.BODY
		} else {
			HttpLoggingInterceptor.Level.NONE
		}
		return logging
	}

	@Provides
	@Reusable
	fun provideOkHttpClient(
		logging: HttpLoggingInterceptor
	): OkHttpClient {
		return if (BuildConfig.DEBUG)
			OkHttpClient().newBuilder()
				.addNetworkInterceptor(StethoInterceptor())
				.addInterceptor(logging)
				.connectTimeout(NET_TIMEOUT, TimeUnit.SECONDS)
				.build()
		else
			OkHttpClient().newBuilder()
				.addInterceptor(logging)
				.connectTimeout(NET_TIMEOUT, TimeUnit.SECONDS)
				.build()
	}

	@Provides
	fun providesMoshi(): Moshi {
		return Moshi.Builder()
			.add(ZonedDateTimeMoshiAdapter())
			.add(KotlinJsonAdapterFactory())
			.build()
	}


	@Provides
	@Reusable
	fun provideRetrofit(
		moshi: Moshi,
		okHttpClient: OkHttpClient
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl("http://localhost:8080/")
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.client(okHttpClient)
			.build()
	}

}
