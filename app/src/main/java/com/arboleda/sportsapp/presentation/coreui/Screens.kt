package com.arboleda.sportsapp.presentation.coreui

import com.arboleda.sportsapp.util.Constants.Companion.DEFAULT_TIME_ZONE
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object SelectCountry : Screen()

    @Serializable
    data class SelectLeague(
        val countryCode: String,
    ) : Screen()

    @Serializable
    data class Fixtures(
        val timeZone: String = DEFAULT_TIME_ZONE,
        val leagueId: Int,
        val season: Int,
    ) : Screen()
}
