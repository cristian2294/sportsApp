package com.arboleda.sportsapp.domain.models.fixtures

data class Fixtures(
    val errors: List<Any>,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int,
)
