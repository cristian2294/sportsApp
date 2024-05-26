package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Extratime as ExtratimeDomain

data class Extratime(
    @SerializedName("away")
    val away: Any?,
    @SerializedName("home")
    val home: Any?,
) {
    fun toDomain(): ExtratimeDomain {
        return ExtratimeDomain(
            away = away,
            home = home,
        )
    }
}
