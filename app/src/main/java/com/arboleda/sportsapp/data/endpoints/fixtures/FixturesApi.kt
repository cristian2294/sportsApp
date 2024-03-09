package com.arboleda.sportsapp.data.endpoints.fixtures

import com.arboleda.sportsapp.data.models.fixtures.Fixtures
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FixturesApi {
    @GET("fixtures")
    suspend fun getAllFixtures(
        @Query("league") league: Int,
        @Query("season") season: Int,
    ): Response<Fixtures>
}
