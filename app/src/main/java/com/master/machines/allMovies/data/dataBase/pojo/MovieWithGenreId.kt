package com.master.machines.allMovies.data.dataBase.pojo

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.master.machines.allMovies.data.dataBase.entity.GenreIdsEntity
import com.master.machines.allMovies.data.dataBase.entity.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieWithGenreId(
    @Embedded var movie: MovieEntity = MovieEntity(), @Relation(
        parentColumn = "id", entityColumn = "idMovie"
    ) var listGenreIds: List<GenreIdsEntity> = listOf()
) : Parcelable