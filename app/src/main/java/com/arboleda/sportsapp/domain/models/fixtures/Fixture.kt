package com.arboleda.sportsapp.domain.models.fixtures

data class Fixture(
    val date: String,
    val id: Int,
    val periods: Periods? = null,
    val referee: Any? = null,
    val status: Status,
    val timestamp: Int,
    val timezone: String,
    val venue: Venue,
)
