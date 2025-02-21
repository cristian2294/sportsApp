package com.arboleda.sportsapp.di

import com.arboleda.sportsapp.data.endpoints.countries.CountriesApi
import com.arboleda.sportsapp.data.repositories.countries.CountriesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import com.arboleda.sportsapp.domain.usecases.countries.CountriesUC
import com.arboleda.sportsapp.domain.usecases.countries.GetAllCountriesUC
import com.arboleda.sportsapp.domain.usecases.countries.GetCountryCodeUC
import com.arboleda.sportsapp.domain.usecases.countries.SetCountryCodeUC
import com.arboleda.sportsapp.presentation.states.CountriesState
import com.arboleda.sportsapp.presentation.viewmodels.countries.CountriesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {
    @Singleton
    @Provides
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi = retrofit.create(CountriesApi::class.java)

    @Singleton
    @Provides
    fun provideCountriesRepository(
        datastorePreferencesRepository: DatastorePreferencesRepository,
        countriesApi: CountriesApi,
    ): CountriesRepository =
        CountriesRepositoryImpl(
            datastorePreferencesRepository = datastorePreferencesRepository,
            countriesApi = countriesApi,
        )

    @Singleton
    @Provides
    fun provideGetAllCountriesUC(countriesRepository: CountriesRepository): GetAllCountriesUC =
        GetAllCountriesUC(countriesRepository = countriesRepository)

    @Singleton
    @Provides
    fun provideGetCountryCodeUC(countriesRepository: CountriesRepository): GetCountryCodeUC =
        GetCountryCodeUC(countriesRepository = countriesRepository)

    @Singleton
    @Provides
    fun provideSetCountryCodeUC(countriesRepository: CountriesRepository): SetCountryCodeUC =
        SetCountryCodeUC(countriesRepository = countriesRepository)

    @Singleton
    @Provides
    fun provideCountriesUC(
        getAllCountriesUC: GetAllCountriesUC,
        getCountriesUC: GetCountryCodeUC,
        setCountryCodeUC: SetCountryCodeUC,
    ): CountriesUC =
        CountriesUC(
            getAllCountriesUC = getAllCountriesUC,
            getCountryCodeUC = getCountriesUC,
            setCountryCode = setCountryCodeUC,
        )

    @Singleton
    @Provides
    fun provideCountriesViewModel(countriesUC: CountriesUC): CountriesViewModel =
        CountriesViewModel(
            countriesUC = countriesUC,
            _countriesState = MutableStateFlow(CountriesState.Loading),
            _showDialog = MutableStateFlow(false),
            _countryCode = MutableStateFlow(String()),
            ioDispatcher = DispatchersModule.providerIoDispatcher(),
        )
}
