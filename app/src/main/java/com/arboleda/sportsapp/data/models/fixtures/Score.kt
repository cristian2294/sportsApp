package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Score as ScoreDomain

data class Score(
    @SerializedName("extratime")
    val extratime: Extratime,
    @SerializedName("fulltime")
    val fulltime: Fulltime,
    @SerializedName("halftime")
    val halftime: Halftime,
    @SerializedName("penalty")
    val penalty: Penalty,
) {
    fun toDomain(): ScoreDomain {
        return ScoreDomain(
            extratime = extratime.toDomain(),
            fulltime = fulltime.toDomain(),
            halftime = halftime.toDomain(),
            penalty = penalty.toDomain(),
        )
    }
}
