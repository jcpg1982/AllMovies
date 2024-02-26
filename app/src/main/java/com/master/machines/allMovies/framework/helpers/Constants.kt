package com.master.machines.allMovies.framework.helpers

import com.master.machines.allMovies.BuildConfig

object Constants {

    const val PATH_NAME_APP = BuildConfig.APP_NAME
    const val NAME_DATA_BASE = "$PATH_NAME_APP.db"

    const val APY_KEY = "f46b58478f489737ad5a4651a4b25079"
    const val URL_IMAGE = "https://image.tmdb.org/t/p/w500/"

    object Credentials {
        const val USERNAME = "Admin"
        const val PASSWORD = "Password*123"
    }

    object Routes {
        const val LOGIN = "login"
        const val HOME = "home"
        const val DETAIL = "detail"
    }

    object TileRoutes {
        const val TITLE_HOME = "Inicio"
    }

    object Regex {
        val ONLY_LETTERS = Regex("^[a-zA-ZáéíóúñÑÁÉÍÓÚ@.,+\\s]*$")
        val ONLY_NUMBERS = Regex("^[0-9.,\\s]*$")
        val MIXTO = Regex("^[a-zA-Z0-9áéíóúñÑÁÉÍÓÚ@*.,+()/\\s]*$")
    }
}