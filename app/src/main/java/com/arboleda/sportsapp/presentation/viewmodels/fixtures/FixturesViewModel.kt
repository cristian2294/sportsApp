package com.arboleda.sportsapp.presentation.viewmodels.fixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.di.IoDispatcher
import com.arboleda.sportsapp.domain.usecases.fixtures.FixturesUC
import com.arboleda.sportsapp.presentation.states.FixturesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel
    @Inject
    constructor(
        private val fixturesUC: FixturesUC,
        private val _fixtures: MutableStateFlow<FixturesState>,
        private val _showDialog: MutableStateFlow<Boolean>,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        val fixturesState: StateFlow<FixturesState> get() = _fixtures
        val showDialog: StateFlow<Boolean> get() = _showDialog

        fun getAllFixtures(
            timeZone: String,
            league: Int,
            season: Int,
        ) {
            viewModelScope.launch {
                withContext(ioDispatcher) {
                    fixturesUC.getAllFixturesUC(timeZone, league, season)
                        .onStart { _fixtures.value = FixturesState.Loading }
                        .catch {
                            _fixtures.value = FixturesState.Error(it.message.orEmpty())
                        }
                        .collect { fixtures ->
                            _fixtures.value = FixturesState.Success(fixtures.response)
                        }
                }
            }
        }

        fun onDialogDismiss() {
            _showDialog.value = false
        }
    }
