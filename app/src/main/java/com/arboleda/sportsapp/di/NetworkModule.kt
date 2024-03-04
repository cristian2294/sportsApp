package com.arboleda.sportsapp.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.arboleda.sportsapp.data.endpoints.countries.CountriesApi
import com.arboleda.sportsapp.data.repositories.countries.CountriesRepositoryImpl
import com.arboleda.sportsapp.data.repositories.preferences.DatastorePreferencesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.presentation.states.CountriesState
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(SportInterceptor())
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

    @Singleton
    @Provides
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCountriesRepository(
        datastorePreferencesRepository: DatastorePreferencesRepository,
        countriesApi: CountriesApi,
    ): CountriesRepository {
        return CountriesRepositoryImpl(
            datastorePreferencesRepository = datastorePreferencesRepository,
            countriesApi = countriesApi,
        )
    }

    @Singleton
    @Provides
    fun provideCountriesUC(countriesRepository: CountriesRepository): CountriesUC {
        return CountriesUC(countriesRepository = countriesRepository)
    }

    @Singleton
    @Provides
    fun provideCountriesViewModel(countriesUC: CountriesUC): CountriesViewModel {
        return CountriesViewModel(
            countriesUC = countriesUC,
            _countriesState = MutableLiveData<CountriesState>(),
            _showDialog = MutableLiveData<Boolean>(),
            _countryCode = MutableLiveData<String>(),
        )
    }
}

const val BASE_URL = "https://v3.football.api-sports.io/"
