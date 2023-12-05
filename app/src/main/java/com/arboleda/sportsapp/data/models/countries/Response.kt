package com.arboleda.sportsapp.data.models.countries

import com.arboleda.sportsapp.domain.models.countries.Response
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String,
) {
    fun toDomain(): Response {
        return Response(
            code = code,
            flag = flag,
            name = name,
        )
    }
}
