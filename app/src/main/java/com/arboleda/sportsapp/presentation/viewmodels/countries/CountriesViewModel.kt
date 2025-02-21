package com.arboleda.sportsapp.presentation.viewmodels.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.di.IoDispatcher
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.presentation.states.CountriesState
import com.arboleda.sportsapp.util.Constants.Companion.COUNTRY_CODE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel
    @Inject
    constructor(
        private val countriesUC: CountriesUC,
        private val _countriesState: MutableStateFlow<CountriesState>,
        private val _showDialog: MutableStateFlow<Boolean>,
        private val _countryCode: MutableStateFlow<String>,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        val countriesState: StateFlow<CountriesState> get() = _countriesState

        val showDialog: StateFlow<Boolean> get() = _showDialog

        val countryCode: StateFlow<String> get() = _countryCode

        fun getAllCountries() {
            _countriesState.value = CountriesState.Loading
            viewModelScope.launch {
                try {
                    val countries = withContext(ioDispatcher) { countriesUC.getAllCountriesUC }
                    val countriesDomain =
                        countries.invoke().response.map {
                            it.toDomain()
                        }
                    _countriesState.value = CountriesState.Success(countriesDomain)
                } catch (e: Exception) {
                    _countriesState.value = CountriesState.Error(e.message)
                }
            }
        }

        fun saveCountryCode(value: String) {
            viewModelScope.launch {
                withContext(ioDispatcher) {
                    countriesUC.setCountryCode(COUNTRY_CODE_KEY, value)
                }
            }
        }

        fun getCountryCode() {
            viewModelScope.launch {
                _countryCode.value = countriesUC.getCountryCodeUC(COUNTRY_CODE_KEY) ?: String()
            }
        }

        fun onDialogDismiss() {
            _showDialog.value = false
        }
    }
