package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.League as LeagueDomain

data class League(
    @SerializedName("country")
    val country: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("round")
    val round: String,
    @SerializedName("season")
    val season: Int,
) {
    fun toDomain(): LeagueDomain {
        return LeagueDomain(
            country = country,
            flag = flag,
            id = id,
            logo = logo,
            name = name,
            round = round,
            season = season,
        )
    }
}
