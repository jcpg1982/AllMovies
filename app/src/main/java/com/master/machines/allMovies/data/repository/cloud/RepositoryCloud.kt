package com.master.machines.allMovies.data.repository.cloud

import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import com.master.machines.allMovies.data.repository.WebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.master.machines.allMovies.data.repository.dataSource.DataSource
import retrofit2.Response
import javax.inject.Inject

class RepositoryCloud @Inject constructor(private val webService: WebService) :
    DataSource.DataSourceCloud {

    override suspend fun getAllMovies(
        page: Int, apiKey: String
    ): Flow<Response<ResponseAllMoviesDTO>> = flow {
        val result = webService.getAllMovies(page, apiKey)
        emit(result)
    }
}