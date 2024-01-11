package com.arboleda.sportsapp.domain.repositories.countries

import com.arboleda.sportsapp.domain.models.countries.Countries

interface CountriesRepository {
    suspend fun getAllCountries(): Countries

    suspend fun setCountryCode(key: String, value: String)

    suspend fun getCountryCode(key: String): String?
}
