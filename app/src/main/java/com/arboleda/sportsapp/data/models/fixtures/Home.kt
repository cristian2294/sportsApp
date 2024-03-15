package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Home as HomeDomain

data class Home(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("winner")
    val winner: Boolean,
) {
    fun toDomain(): HomeDomain {
        return HomeDomain(
            id = id,
            logo = logo,
            name = name,
            winner = winner,
        )
    }
}
