package com.arboleda.sportsapp.data.models.countries

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String,
)
