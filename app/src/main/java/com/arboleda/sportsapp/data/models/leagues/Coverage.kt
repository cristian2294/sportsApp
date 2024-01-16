package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Coverage as CoverageDomain

data class Coverage(
    @SerializedName("fixtures")
    val fixtures: Fixture,
    @SerializedName("standings")
    val standings: Boolean,
    @SerializedName("players")
    val players: Boolean,
    @SerializedName("top_scorers")
    val topScorers: Boolean,
    @SerializedName("top_assists")
    val topAssists: Boolean,
    @SerializedName("top_cards")
    val topCards: Boolean,
    @SerializedName("injuries")
    val injuries: Boolean,
    @SerializedName("predictions")
    val predictions: Boolean,
    @SerializedName("odds")
    val odds: Boolean,
) {
    fun toDomain(): CoverageDomain {
        return CoverageDomain(
            fixtures = fixtures.toDomain(),
            standings = standings,
            players = players,
            topScorers = topScorers,
            topAssists = topAssists,
            topCards = topCards,
            injuries = injuries,
            predictions = predictions,
            odds = odds,
        )
    }
}
