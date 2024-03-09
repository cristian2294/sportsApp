package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("away")
    val away: Away,
    @SerializedName("home")
    val home: Home,
)
