package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Fulltime as FulltimeDomain

data class Fulltime(
    @SerializedName("away")
    val away: Any?,
    @SerializedName("home")
    val home: Any?,
) {
    fun toDomain(): FulltimeDomain {
        return FulltimeDomain(
            away = away,
            home = home,
        )
    }
}
