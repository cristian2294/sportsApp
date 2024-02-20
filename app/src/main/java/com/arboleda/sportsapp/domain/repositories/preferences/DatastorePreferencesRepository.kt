package com.arboleda.sportsapp.domain.repositories.preferences

interface DatastorePreferencesRepository {

    // Country
    suspend fun setCountryCode(key: String, value: String)

    suspend fun getCountryCode(key: String): String?

    // Leagues
    suspend fun setLeagueId(key: String, value: Int)

    suspend fun getLeagueId(key: String): Int?
}
