package com.arboleda.sportsapp.presentation.viewmodels.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.presentation.states.CountriesState
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
}
