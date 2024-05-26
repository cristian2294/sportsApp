package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Teams as TeamsDomain

data class Teams(
    @SerializedName("away")
    val away: Away,
    @SerializedName("home")
    val home: Home,
) {
    fun toDomain(): TeamsDomain {
        return TeamsDomain(
            away = away.toDomain(),
            home = home.toDomain(),
        )
    }
}
