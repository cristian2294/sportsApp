package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Halftime as HalftimeDomain

data class Halftime(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int,
) {
    fun toDomain(): HalftimeDomain {
        return HalftimeDomain(
            away = away,
            home = home,
        )
    }
}
