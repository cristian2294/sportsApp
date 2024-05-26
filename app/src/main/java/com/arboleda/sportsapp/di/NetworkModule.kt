package com.arboleda.sportsapp.di

import android.content.Context
import com.arboleda.sportsapp.data.repositories.preferences.DatastorePreferencesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val client =
            OkHttpClient.Builder()
                .addInterceptor(SportInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDatastorePreferences(
        @ApplicationContext context: Context,
    ): DatastorePreferencesRepository {
        return DatastorePreferencesRepositoryImpl(context = context)
    }
}

const val BASE_URL = "https://v3.football.api-sports.io/"
