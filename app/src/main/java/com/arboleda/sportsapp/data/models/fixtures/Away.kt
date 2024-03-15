package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Away as AwayDomain

data class Away(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("winner")
    val winner: Boolean,
) {
    fun toDomain(): AwayDomain {
        return AwayDomain(
            id = id,
            logo = logo,
            name = name,
            winner = winner,
        )
    }
}
