package com.master.machines.allMovies.data.models

import android.os.Parcelable
import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var genre_ids: List<Int>? = null,
    var id: Int? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
) : Parcelable {
    constructor(movieWithGenreId: MovieWithGenreId) : this() {
        val movie = movieWithGenreId.movie
        val listId = movieWithGenreId.listGenreIds
        adult = movie.adult
        backdrop_path = movie.backdropPath
        genre_ids = listId.map { it.genreId ?: 0 }
        id = movie.id
        original_language = movie.originalLanguage
        original_title = movie.originalTitle
        overview = movie.overview
        popularity = movie.popularity
        poster_path = movie.posterPath
        release_date = movie.releaseDate
        title = movie.title
        video = movie.video
        vote_average = movie.voteAverage
        vote_count = movie.voteCount
    }
}