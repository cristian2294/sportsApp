package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Season as SeasonDomain

data class Season(
    @SerializedName("year")
    val year: String,
    @SerializedName("start")
    val start: String,
    @SerializedName("current")
    val current: Boolean,
    @SerializedName("coverage")
    val coverage: Coverage,
) {
    fun toDomain(): SeasonDomain = SeasonDomain(
        year = year,
        start = start,
        current = current,
        coverage = coverage.toDomain(),
    )
}
