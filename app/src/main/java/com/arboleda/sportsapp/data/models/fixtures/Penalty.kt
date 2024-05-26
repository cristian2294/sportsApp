package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Penalty as PenaltyDomain

data class Penalty(
    @SerializedName("away")
    val away: Any?,
    @SerializedName("home")
    val home: Any?,
) {
    fun toDomain(): PenaltyDomain {
        return PenaltyDomain(
            away = away,
            home = home,
        )
    }
}
