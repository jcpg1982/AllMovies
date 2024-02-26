package com.master.machines.allMovies.data.dataBase.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.master.machines.allMovies.data.dataBase.entity.MovieEntity
import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import org.jetbrains.annotations.NotNull

@Dao
interface DaoMovie {

    @Query("DELETE  FROM MovieEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(@NotNull entities: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(@NotNull entities: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id NOT IN (:listId) ORDER BY id ASC")
    fun getListAll(listId: List<Int>): PagingSource<Int, MovieWithGenreId>

    @Query("SELECT * FROM MovieEntity ORDER BY id ASC")
    fun getListAll(): PagingSource<Int, MovieWithGenreId>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getItem(id: Int): MovieWithGenreId?
}