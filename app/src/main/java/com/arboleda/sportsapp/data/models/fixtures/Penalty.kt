package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("away")
    val away: Any,
    @SerializedName("home")
    val home: Any,
)
