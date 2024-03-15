package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Venue as VenueDomain

data class Venue(
    @SerializedName("city")
    val city: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
) {
    fun toDomain(): VenueDomain {
        return VenueDomain(
            city = city,
            id = id,
            name = name,
        )
    }
}
