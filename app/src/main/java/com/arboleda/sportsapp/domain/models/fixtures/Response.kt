package com.arboleda.sportsapp.domain.models.fixtures

data class Response(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams,
)
