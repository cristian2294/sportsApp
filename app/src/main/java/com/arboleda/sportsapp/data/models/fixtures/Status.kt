package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Status as StatusDomain

data class Status(
    @SerializedName("elapsed")
    val elapsed: Int,
    @SerializedName("long")
    val long: String,
    @SerializedName("short")
    val short: String,
) {
    fun toDomain(): StatusDomain {
        return StatusDomain(
            elapsed = elapsed,
            long = long,
            short = short,
        )
    }
}
