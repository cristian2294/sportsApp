package com.arboleda.sportsapp.presentation.states

import com.arboleda.sportsapp.domain.models.fixtures.Response

sealed class FixturesState {
    object Loading : FixturesState()

    data class Error(val message: String?) : FixturesState()

    data class Success(val fixtures: List<Response>) : FixturesState()
}
