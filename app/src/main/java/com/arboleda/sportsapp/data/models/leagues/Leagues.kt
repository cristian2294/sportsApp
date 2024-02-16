package com.arboleda.sportsapp.data.models.leagues

import com.arboleda.sportsapp.domain.models.leagues.Leagues
import com.google.gson.annotations.SerializedName

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
    fun toDomain(): Leagues {
        return Leagues(
            parameters = parameters.toDomain(),
            errors = errors,
            results = results,
            paging = paging.toDomain(),
            response = response.map { it.toDomain() },
        )
    }
}
