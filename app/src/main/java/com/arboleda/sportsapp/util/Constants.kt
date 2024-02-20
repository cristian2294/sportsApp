package com.arboleda.sportsapp.util

class Constants {
    companion object {
        const val COUNTRY_CODE_KEY = "country_code"
        const val LEAGUE_ID_KEY = "league_id"

        // routes
        const val HOME_SCREEN = "home_screen"
        const val SELECT_COUNTRY_SCREEN = "select_country_screen"
        const val SELECT_LEAGUE_SCREEN = "select_league_screen/{country_code}"
        const val COUNTRY_CODE = "country_code"
    }
}
