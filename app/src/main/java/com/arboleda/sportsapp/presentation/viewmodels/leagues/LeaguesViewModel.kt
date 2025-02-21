package com.arboleda.sportsapp.presentation.viewmodels.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.di.IoDispatcher
import com.arboleda.sportsapp.domain.usecases.leagues.LeaguesUC
import com.arboleda.sportsapp.presentation.states.LeagueState
import com.arboleda.sportsapp.util.Constants.Companion.LEAGUE_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel
    @Inject
    constructor(
        private val leaguesUC: LeaguesUC,
        private val _leagueState: MutableStateFlow<LeagueState>,
        private val _showDialog: MutableStateFlow<Boolean>,
        private val _leagueId: MutableStateFlow<Int>,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        val showDialog: StateFlow<Boolean> get() = _showDialog
        val leagueState: StateFlow<LeagueState> get() = _leagueState

        val leagueId: StateFlow<Int> get() = _leagueId

        fun getAllLeagues(countryCode: String) {
            _leagueState.value = LeagueState.Loading
            viewModelScope.launch {
                try {
                    val leagues = withContext(ioDispatcher) { leaguesUC.getAllLeaguesUC(countryCode) }
                    val leaguesDomain = leagues.response
                    _leagueState.value = LeagueState.Success(leaguesDomain)
                } catch (e: Exception) {
                    _leagueState.value = LeagueState.Error(e.message)
                }
            }
        }

        fun saveLeagueId(value: Int) {
            viewModelScope.launch {
                withContext(ioDispatcher) {
                    leaguesUC.setLeagueIdUC(LEAGUE_ID_KEY, value)
                }
            }
        }

        fun getLeagueId() {
            viewModelScope.launch {
                _leagueId.value = leaguesUC.getLeagueIdUC(LEAGUE_ID_KEY) ?: 0
            }
        }

        fun onDialogDismiss() {
            _showDialog.value = false
        }
    }
