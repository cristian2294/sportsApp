package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Parameters as ParametersDomain

data class Parameters(
    @SerializedName("live")
    val live: String?,
) {
    fun toDomain(): ParametersDomain {
        return ParametersDomain(live = live)
    }
}
