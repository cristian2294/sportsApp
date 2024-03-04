package com.arboleda.sportsapp.data.endpoints.leagues

import com.arboleda.sportsapp.data.models.leagues.Leagues
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LeaguesApi {
    @GET("leagues")
    suspend fun getAllLeagues(
        @Query("code") countryCode: String,
    ): Response<Leagues>
}
