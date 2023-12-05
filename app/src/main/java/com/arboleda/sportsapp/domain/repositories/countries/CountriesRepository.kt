package com.arboleda.sportsapp.domain.repositories.countries

import com.arboleda.sportsapp.domain.models.countries.Countries

interface CountriesRepository {
    suspend fun getAllCountries(): Countries
}
