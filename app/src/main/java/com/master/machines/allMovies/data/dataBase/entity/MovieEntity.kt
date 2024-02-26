package com.master.machines.allMovies.data.dataBase.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.master.machines.allMovies.data.models.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class MovieEntity(
    @PrimaryKey
    var id: Int? = null,
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
) : Parcelable {

    constructor(movie: Movie) : this() {
        this.id = movie.id
        this.adult = movie.adult
        this.backdropPath = movie.backdrop_path
        this.originalLanguage = movie.original_language
        this.originalTitle = movie.original_title
        this.overview = movie.overview
        this.popularity = movie.popularity
        this.posterPath = movie.poster_path
        this.releaseDate = movie.release_date
        this.title = movie.title
        this.video = movie.video
        this.voteAverage = movie.vote_average
        this.voteCount = movie.vote_count
    }
}