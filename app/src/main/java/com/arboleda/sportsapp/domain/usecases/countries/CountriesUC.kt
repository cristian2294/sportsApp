package com.arboleda.sportsapp.domain.usecases.countries

import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import javax.inject.Inject

class CountriesUC @Inject constructor(private val countriesRepository: CountriesRepository) {
    suspend operator fun invoke() = countriesRepository.getAllCountries()

    suspend fun setCountryCode(key: String, value: String) {
        countriesRepository.setCountryCode(key, value)
    }

    suspend fun getCountryCode(key: String): String? = countriesRepository.getCountryCode(key)
}
