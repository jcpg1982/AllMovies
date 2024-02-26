package com.master.machines.allMovies.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.master.machines.allMovies.BuildConfig
import com.master.machines.allMovies.data.repository.WebService
import com.master.machines.allMovies.data.repository.cloud.RepositoryCloud
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetWorkModule {

    private val TAG = NetWorkModule::class.java.simpleName

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Log.w(TAG, "provideHttpLoggingInterceptor message: $message")
        }.apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Provides
    @Singleton
    fun provideCacheFile(context: Context) = File(context.cacheDir, "okHttpCache")

    @Provides
    @Singleton
    fun provideCache(file: File): Cache = Cache(file, 10 * 10000 * 10000)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .readTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(WebService::class.java)

    @Provides
    @Singleton
    fun provideDataSourceRemote(webService: WebService) = RepositoryCloud(webService)
}