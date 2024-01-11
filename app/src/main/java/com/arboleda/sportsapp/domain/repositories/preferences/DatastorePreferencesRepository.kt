package com.arboleda.sportsapp.domain.repositories.preferences

interface DatastorePreferencesRepository {

    suspend fun setCountryCode(key: String, value: String)

    suspend fun getCountryCode(key: String): String?
}
