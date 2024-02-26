package com.master.machines.allMovies.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.master.machines.allMovies.data.dataBase.entity.GenreIdsEntity
import org.jetbrains.annotations.NotNull

@Dao
interface DaoGenreId {

    @Query("DELETE  FROM GenreIdsEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(@NotNull entities: List<GenreIdsEntity>)

    @Query("SELECT * FROM GenreIdsEntity")
    suspend fun getListAll(): List<GenreIdsEntity>
}