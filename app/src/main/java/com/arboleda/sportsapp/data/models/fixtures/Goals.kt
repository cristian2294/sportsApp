package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int,
)
