package com.arboleda.sportsapp.util

sealed class Routes(val route: String) {
    object selectCountryScreen : Routes(SELECT_COUNTRY_SCREEN)
    object selectLeagueScreen : Routes(SELECT_LEAGUE_SCREEN) {
        fun createRoute(countryCode: String) = "select_league_screen/$countryCode"
    }
}
