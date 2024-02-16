package com.arboleda.sportsapp.presentation.viewmodels.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.domain.usecases.leagues.LeaguesUC
import com.arboleda.sportsapp.presentation.states.LeagueState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val leaguesUC: LeaguesUC,
    private val _leagueState: MutableLiveData<LeagueState>,
    private val _showDialog: MutableLiveData<Boolean>,
) : ViewModel() {
    val showDialog: LiveData<Boolean> get() = _showDialog
    val leagueState: LiveData<LeagueState> get() = _leagueState

    fun getAllLeagues(countryCode: String) {
        _leagueState.value = LeagueState.Loading
        viewModelScope.launch {
            try {
                val leagues = withContext(Dispatchers.IO) { leaguesUC.invoke(countryCode) }
                val leaguesDomain = leagues.response
                _leagueState.value = LeagueState.Success(leaguesDomain)
            } catch (e: Exception) {
                _leagueState.value = LeagueState.Error(e.message)
            }
        }
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }
}
