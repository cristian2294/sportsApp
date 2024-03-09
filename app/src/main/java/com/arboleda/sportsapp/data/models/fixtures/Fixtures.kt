package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName

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
)
