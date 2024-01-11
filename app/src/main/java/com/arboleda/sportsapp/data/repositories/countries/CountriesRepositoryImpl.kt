package com.arboleda.sportsapp.data.repositories.countries

import com.arboleda.sportsapp.data.endpoints.countries.CountriesApi
import com.arboleda.sportsapp.domain.models.countries.Countries
import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val datastorePreferencesRepository: DatastorePreferencesRepository,
    private val countriesApi: CountriesApi,
) :
    CountriesRepository {
    override suspend fun getAllCountries(): Countries {
        return countriesApi.getAllCountries().body()?.toDomain()
            ?: throw IllegalStateException("Response body is null")
    }

    override suspend fun setCountryCode(key: String, value: String) {
        datastorePreferencesRepository.setCountryCode(key, value)
    }

    override suspend fun getCountryCode(key: String): String? {
        return datastorePreferencesRepository.getCountryCode(key)
    }
}
