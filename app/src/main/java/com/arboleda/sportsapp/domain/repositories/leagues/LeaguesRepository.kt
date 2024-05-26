package com.arboleda.sportsapp.domain.repositories.leagues

import com.arboleda.sportsapp.domain.models.leagues.Leagues

interface LeaguesRepository {
    suspend fun getAllLeagues(countryCode: String): Leagues

    suspend fun setLeagueId(
        key: String,
        value: Int,
    )

    suspend fun getLeagueId(key: String): Int?
}
