package com.master.machines.allMovies.data.repository.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.master.machines.allMovies.data.dataBase.DataBase
import com.master.machines.allMovies.data.dataBase.entity.GenreIdsEntity
import com.master.machines.allMovies.data.dataBase.entity.MovieEntity
import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.data.repository.dataSource.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryLocal @Inject constructor(dataBase: DataBase) : DataSource.DataSourceLocal {

    private val config = PagingConfig(
        pageSize = 20, prefetchDistance = 1, initialLoadSize = 20, enablePlaceholders = true
    )
    private val movieDao = dataBase.movieDao()
    private val genreIdDao = dataBase.genreIdDao()

    override suspend fun saveAllMovies(listMovies: List<Movie>): Flow<Unit> = flow {
        val result = listMovies.forEach { item ->
            movieDao.insert(MovieEntity(item))
            genreIdDao.insertAll(item.genre_ids?.mapIndexed { index, genreId ->
                GenreIdsEntity((index + 1), item.id ?: 0, genreId)
            } ?: listOf())
        }
        emit(result)
    }

    override suspend fun getListAllMovies(listId: List<Int>): Flow<PagingData<MovieWithGenreId>> {
        val pager: Pager<Int, MovieWithGenreId> = Pager(config) {
            when {
                listId.isEmpty() -> movieDao.getListAll()
                else -> movieDao.getListAll(listId)
            }
        }
        return pager.flow
    }

    override suspend fun getItemMovie(id: Int): Flow<MovieWithGenreId?> = flow {
        val result = movieDao.getItem(id)
        emit(result)
    }
}