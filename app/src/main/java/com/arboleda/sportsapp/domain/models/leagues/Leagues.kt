package com.arboleda.sportsapp.domain.models.leagues

data class Leagues(
    val parameters: Parameters,
    val errors: List<Any>,
    val results: Int,
    val paging: Paging,
    val response: List<Response>,
)
