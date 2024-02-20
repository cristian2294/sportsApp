package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Parameters as ParametersDomain

data class Parameters(
    @SerializedName("id")
    val id: Int,
) {
    fun toDomain(): ParametersDomain = ParametersDomain(id = id)
}
