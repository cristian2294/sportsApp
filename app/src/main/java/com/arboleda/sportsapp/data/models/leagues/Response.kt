package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Response as ResponseDomain

data class Response(
    @SerializedName("league")
    val league: League,
    @SerializedName("country")
    val country: Country,
    @SerializedName("seasons")
    val seasons: List<Season>,
) {
    fun toDomain(): ResponseDomain = ResponseDomain(
        league.toDomain(),
        country.toDomain(),
        seasons.map { it.toDomain() },
    )
}
