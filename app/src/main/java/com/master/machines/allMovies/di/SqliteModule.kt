package com.master.machines.allMovies.di

import android.content.Context
import com.master.machines.allMovies.data.dataBase.DataBase
import com.master.machines.allMovies.data.repository.local.RepositoryLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SqliteModule {

    @Provides
    @Singleton
    fun appDatabase(context: Context): DataBase = DataBase.getDatabase(context)


    @Provides
    @Singleton
    fun providerRepositoryLocal(dataBase: DataBase) = RepositoryLocal(dataBase)
}