package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("elapsed")
    val elapsed: Int,
    @SerializedName("long")
    val long: String,
    @SerializedName("short")
    val short: String,
)
