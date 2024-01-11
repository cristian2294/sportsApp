package com.arboleda.sportsapp.data.repositories.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import kotlinx.coroutines.flow.first
import java.lang.Exception
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences_name"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class DatastorePreferencesRepositoryImpl @Inject constructor(private val context: Context) :
    DatastorePreferencesRepository {
    override suspend fun setCountryCode(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getCountryCode(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
