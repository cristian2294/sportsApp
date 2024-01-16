package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Leagues as LeaguesDomain

data class Leagues(
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("results")
    val results: Int,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("response")
    val response: List<Response>,
) {
    fun toDomain(): LeaguesDomain {
        return LeaguesDomain(
            parameters.toDomain(),
            errors,
            results,
            paging.toDomain(),
            response.map { it.toDomain() },
        )
    }
}
