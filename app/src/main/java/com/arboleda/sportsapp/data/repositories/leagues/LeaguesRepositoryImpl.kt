package com.arboleda.sportsapp.data.repositories.leagues

import com.arboleda.sportsapp.data.endpoints.leagues.LeaguesApi
import com.arboleda.sportsapp.domain.models.leagues.Leagues
import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import javax.inject.Inject

class LeaguesRepositoryImpl
    @Inject
    constructor(
        private val datastorePreferencesRepository: DatastorePreferencesRepository,
        private val leaguesApi: LeaguesApi,
    ) :
    LeaguesRepository {
        override suspend fun getAllLeagues(countryCode: String): Leagues {
            return leaguesApi.getAllLeagues(countryCode).body()?.toDomain()
                ?: throw IllegalStateException("Response body is null")
        }

        override suspend fun setLeagueId(
            key: String,
            value: Int,
        ) {
            datastorePreferencesRepository.setLeagueId(key, value)
        }

        override suspend fun getLeagueId(key: String): Int? {
            return datastorePreferencesRepository.getLeagueId(key)
        }
    }
