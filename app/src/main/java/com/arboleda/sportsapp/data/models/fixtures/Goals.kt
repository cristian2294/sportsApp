package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Goals as GoalsDomain

data class Goals(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int,
) {
    fun toDomain(): GoalsDomain {
        return GoalsDomain(
            away = away,
            home = home,
        )
    }
}
