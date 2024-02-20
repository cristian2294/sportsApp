package com.arboleda.sportsapp.domain.models.leagues

data class Coverage(
    val fixtures: Fixture,
    val standings: Boolean,
    val players: Boolean,
    val topScorers: Boolean,
    val topAssists: Boolean,
    val topCards: Boolean,
    val injuries: Boolean,
    val predictions: Boolean,
    val odds: Boolean,
)
