package com.arboleda.sportsapp.presentation.states

import com.arboleda.sportsapp.domain.models.leagues.Response

sealed class LeagueState {
    data class Error(val message: String?) : LeagueState()
    object Loading : LeagueState()
    data class Success(val leagues: List<Response>) : LeagueState()
}
