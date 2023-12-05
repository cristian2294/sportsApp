package com.arboleda.sportsapp.data.models.countries

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.countries.Countries as CountriesDomain

data class Countries(
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("get")
    val get: String,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("parameters")
    val parameters: List<Parameters>,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int,
) {
    fun toDomain(): CountriesDomain {
        return CountriesDomain(
            errors = errors,
            get = get,
            paging = paging,
            parameters = parameters,
            response = response,
            results = results,
        )
    }
}
