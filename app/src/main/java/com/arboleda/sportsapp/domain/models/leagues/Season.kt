package com.arboleda.sportsapp.domain.models.leagues

data class Season(
    val year: String,
    val start: String,
    val current: Boolean,
    val coverage: Coverage,
)
