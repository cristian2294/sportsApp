package com.arboleda.sportsapp.data.models.leagues

import com.arboleda.sportsapp.domain.models.leagues.Response
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("league")
    val league: League,
    @SerializedName("country")
    val country: Country,
    @SerializedName("seasons")
    val seasons: List<Season>,
) {
    fun toDomain(): Response {
        return Response(
            league.toDomain(),
            country.toDomain(),
            seasons.map { it.toDomain() },
        )
    }
}
