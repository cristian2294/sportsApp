package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Fixture as FixtureDomain

data class Fixture(
    @SerializedName("events")
    val events: Boolean,
    @SerializedName("lineups")
    val lineups: Boolean,
    @SerializedName("statistics_fixtures")
    val statisticsFixtures: Boolean,
    @SerializedName("statistics_players")
    val statisticsPlayers: Boolean,
) {
    fun toDomain(): FixtureDomain {
        return FixtureDomain(
            events = events,
            lineups = lineups,
            statisticsFixtures = statisticsFixtures,
            statisticsPlayers = statisticsPlayers,
        )
    }
}
