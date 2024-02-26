package com.master.machines.allMovies.data.models

data class ResponseAllMoviesDTO(
    var dates: Dates? = null,
    var page: Int? = null,
    var results: List<Movie>? = null,
    var total_pages: Int? = null,
    var total_results: Int? = null
)