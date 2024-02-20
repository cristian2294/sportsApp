package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.League as LeagueDomain

data class League(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("logo")
    val logo: String,
) {
    fun toDomain(): LeagueDomain {
        return LeagueDomain(
            id = id,
            name = name,
            type = type,
            logo = logo,
        )
    }
}
