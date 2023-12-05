package com.arboleda.sportsapp.di

import com.arboleda.sportsapp.data.endpoints.countries.CountriesApi
import com.arboleda.sportsapp.data.repositories.countries.CountriesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        val client = OkHttpClient.Builder()
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
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCountriesRepository(countriesApi: CountriesApi): CountriesRepository {
        return CountriesRepositoryImpl(countriesApi = countriesApi)
    }

    @Singleton
    @Provides
    fun provideCountriesUC(countriesRepository: CountriesRepository): CountriesUC {
        return CountriesUC(countriesRepository = countriesRepository)
    }

    @Singleton
    @Provides
    fun provideCountriesViewModel(countriesUC: CountriesUC): CountriesViewModel {
        return CountriesViewModel(countriesUC = countriesUC)
    }
}

const val BASE_URL = "https://v3.football.api-sports.io/"
