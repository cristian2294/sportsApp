package com.arboleda.sportsapp.presentation.viewmodels.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.presentation.states.CountriesState
import com.arboleda.sportsapp.util.COUNTRY_CODE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUC: CountriesUC,
) : ViewModel() {

    private var _countriesState = MutableLiveData<CountriesState>()
    val countriesState: LiveData<CountriesState> get() = _countriesState

    private var _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> get() = _showDialog

    private var _countryCode = MutableLiveData<String>()
    val countryCode: LiveData<String> get() = _countryCode

    init {
        getCountryCode()
    }

    fun getAllCountries() {
        _countriesState.value = CountriesState.Loading
        viewModelScope.launch {
            try {
                val countries = withContext(Dispatchers.IO) { countriesUC.invoke() }
                val countriesDomain = countries.response.map {
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
            withContext(Dispatchers.IO) {
                countriesUC.setCountryCode(COUNTRY_CODE_KEY, value)
            }
        }
    }

    fun getCountryCode() {
        viewModelScope.launch {
            _countryCode.value = countriesUC.getCountryCode(COUNTRY_CODE_KEY) ?: String()
        }
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }
}
