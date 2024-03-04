package com.arboleda.sportsapp.data.endpoints.countries

import com.arboleda.sportsapp.data.models.countries.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("countries")
    suspend fun getAllCountries(): Response<Countries>
}
