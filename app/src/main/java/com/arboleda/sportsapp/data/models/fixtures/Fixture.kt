package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Fixture as FixtureDomain

data class Fixture(
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("periods")
    val periods: Periods?,
    @SerializedName("referee")
    val referee: Any?,
    @SerializedName("status")
    val status: Status,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("venue")
    val venue: Venue,
) {
    fun toDomain(): FixtureDomain {
        return FixtureDomain(
            date = date,
            id = id,
            periods = periods?.toDomain(),
            referee = referee,
            status = status.toDomain(),
            timestamp = timestamp,
            timezone = timezone,
            venue = venue.toDomain(),
        )
    }
}
