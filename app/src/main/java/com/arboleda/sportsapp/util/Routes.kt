package com.arboleda.sportsapp.util

import com.arboleda.sportsapp.util.Constants.Companion.HOME_SCREEN
import com.arboleda.sportsapp.util.Constants.Companion.SELECT_COUNTRY_SCREEN
import com.arboleda.sportsapp.util.Constants.Companion.SELECT_LEAGUE_SCREEN

sealed class Routes(val route: String) {
    object SelectCountryScreen : Routes(SELECT_COUNTRY_SCREEN)

    object SelectLeagueScreen : Routes(SELECT_LEAGUE_SCREEN) {
        fun createRoute(countryCode: String) = "select_league_screen/$countryCode"
    }

    object HomeScreen : Routes(HOME_SCREEN) {
        fun createRoute(
            timeZone: String,
            leagueId: Int,
            season: Int,
        ) = "fixtures_screen/$timeZone/$leagueId/$season"
    }
}
