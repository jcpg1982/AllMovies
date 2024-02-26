package com.master.machines.allMovies.data.repository.dataSource

import androidx.paging.PagingData
import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class DataSource {

    interface DataSourceLocal {
        suspend fun saveAllMovies(listMovies: List<Movie>): Flow<Unit>
        suspend fun getListAllMovies(listId: List<Int>): Flow<PagingData<MovieWithGenreId>>
        suspend fun getItemMovie(id: Int): Flow<MovieWithGenreId?>
    }

    interface DataSourceCloud {
        suspend fun getAllMovies(
            page: Int, apiKey: String
        ): Flow<Response<ResponseAllMoviesDTO>>
    }
}