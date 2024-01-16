package com.arboleda.sportsapp.domain.models.leagues

data class Response(
    val league: League,
    val country: Country,
    val seasons: List<Season>,
)
