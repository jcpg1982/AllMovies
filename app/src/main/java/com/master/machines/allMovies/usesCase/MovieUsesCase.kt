package com.master.machines.allMovies.usesCase

import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import com.master.machines.allMovies.data.repository.cloud.RepositoryCloud
import com.master.machines.allMovies.data.repository.local.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieUsesCase @Inject constructor(
    private val repositoryCloud: RepositoryCloud,
    private val repositoryLocal: RepositoryLocal
) {

    suspend operator fun invoke(
        page: Int, apiKey: String
    ): Flow<Response<ResponseAllMoviesDTO>> = repositoryCloud.getAllMovies(page, apiKey)

    suspend operator fun invoke(listMovies: List<Movie>): Flow<Unit> =
        repositoryLocal.saveAllMovies(listMovies)

    suspend operator fun invoke(movieId: Int): Flow<MovieWithGenreId?> =
        repositoryLocal.getItemMovie(movieId)
}