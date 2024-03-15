package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Response as ResponseDomain

data class Response(
    @SerializedName("fixture")
    val fixture: Fixture,
    @SerializedName("goals")
    val goals: Goals,
    @SerializedName("league")
    val league: League,
    @SerializedName("score")
    val score: Score,
    @SerializedName("teams")
    val teams: Teams,
) {
    fun toDomain(): ResponseDomain {
        return ResponseDomain(
            fixture = fixture.toDomain(),
            goals = goals.toDomain(),
            league = league.toDomain(),
            score = score.toDomain(),
            teams = teams.toDomain(),
        )
    }
}
