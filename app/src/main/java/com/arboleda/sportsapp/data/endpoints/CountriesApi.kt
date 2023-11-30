package com.arboleda.sportsapp.data.endpoints

import com.arboleda.sportsapp.data.models.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET("countries")
    fun getCountries(): Response<Countries>
}
