package com.master.machines.allMovies.data.dataBase.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GenreIdsEntity(
    @PrimaryKey
    var id: Int? = null,
    var idMovie: Int? = null,
    var genreId: Int? = null
) : Parcelable {
    constructor(id: Int, idMovie: Int, genreId: Int) : this() {
        this.id = id
        this.idMovie = idMovie
        this.genreId = genreId
    }
}