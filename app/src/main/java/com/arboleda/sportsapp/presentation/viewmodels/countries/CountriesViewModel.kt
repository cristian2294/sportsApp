package com.arboleda.sportsapp.presentation.viewmodels.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUC: CountriesUC,
) : ViewModel() {

    fun getAllCountries() {
        viewModelScope.launch {
            countriesUC.invoke()
        }
    }
}
