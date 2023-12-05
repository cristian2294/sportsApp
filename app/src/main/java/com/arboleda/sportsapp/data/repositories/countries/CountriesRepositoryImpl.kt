package com.arboleda.sportsapp.data.repositories.countries

import com.arboleda.sportsapp.data.endpoints.countries.CountriesApi
import com.arboleda.sportsapp.domain.models.countries.Countries
import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi) :
    CountriesRepository {
    override suspend fun getAllCountries(): Countries {
        return countriesApi.getAllCountries().body()?.toDomain()
            ?: throw IllegalStateException("Response body is null")
    }
}
