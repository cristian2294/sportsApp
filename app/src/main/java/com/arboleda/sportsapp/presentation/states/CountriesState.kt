package com.arboleda.sportsapp.presentation.states

import com.arboleda.sportsapp.domain.models.countries.Response

sealed class CountriesState {
    data class Error(
        val message: String?,
    ) : CountriesState()

    data object Loading : CountriesState()

    data class Success(
        val countries: List<Response>,
    ) : CountriesState()
}
