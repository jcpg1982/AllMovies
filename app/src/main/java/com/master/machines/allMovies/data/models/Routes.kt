package com.master.machines.allMovies.data.models

import com.master.machines.allMovies.framework.helpers.Constants

sealed class Routes(val idRoute: String, open val route: String, open var title: String) {

    data object Login : Routes(
        Constants.Routes.LOGIN, Constants.Routes.LOGIN, ""
    )

    data object Home : Routes(
        Constants.Routes.HOME, Constants.Routes.HOME, Constants.TileRoutes.TITLE_HOME
    )

    data class DetailMovie(
        override var route: String = "${Constants.Routes.DETAIL}/{movieId}",
        override var title: String = ""
    ) : Routes(
        Constants.Routes.DETAIL, route, title
    ) {
        companion object {
            fun Int.createDetail(titles: String): Routes {
                return DetailMovie("${Constants.Routes.DETAIL}/$this", titles)
            }
        }
    }
}
