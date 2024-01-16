package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Country as CountryDomain

data class Country(
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String?,
    @SerializedName("flag")
    val flag: String?,
) {
    fun toDomain(): CountryDomain {
        return CountryDomain(
            name = name,
            code = code,
            flag = flag,
        )
    }
}
