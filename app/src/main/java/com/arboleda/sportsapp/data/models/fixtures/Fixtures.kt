package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Fixtures as FixturesDomain

data class Fixtures(
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int,
) {
    fun toDomain(): FixturesDomain {
        return FixturesDomain(
            errors = errors,
            paging = paging.toDomain(),
            parameters = parameters.toDomain(),
            response = response.map { it.toDomain() },
            results = results,
        )
    }
}
