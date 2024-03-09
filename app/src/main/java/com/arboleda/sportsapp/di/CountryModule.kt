package com.arboleda.sportsapp.di

import androidx.lifecycle.MutableLiveData
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
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {
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
    fun provideGetAllCountriesUC(countriesRepository: CountriesRepository): GetAllCountriesUC {
        return GetAllCountriesUC(countriesRepository = countriesRepository)
    }

    @Singleton
    @Provides
    fun provideGetCountryCodeUC(countriesRepository: CountriesRepository): GetCountryCodeUC {
        return GetCountryCodeUC(countriesRepository = countriesRepository)
    }

    @Singleton
    @Provides
    fun provideSetCountryCodeUC(countriesRepository: CountriesRepository): SetCountryCodeUC {
        return SetCountryCodeUC(countriesRepository = countriesRepository)
    }

    @Singleton
    @Provides
    fun provideCountriesUC(
        getAllCountriesUC: GetAllCountriesUC,
        getCountriesUC: GetCountryCodeUC,
        setCountryCodeUC: SetCountryCodeUC,
    ): CountriesUC {
        return CountriesUC(
            getAllCountriesUC = getAllCountriesUC,
            getCountryCodeUC = getCountriesUC,
            setCountryCode = setCountryCodeUC,
        )
    }

    @Singleton
    @Provides
    fun provideCountriesViewModel(countriesUC: CountriesUC): CountriesViewModel {
        return CountriesViewModel(
            countriesUC = countriesUC,
            _countriesState = MutableLiveData<CountriesState>(),
            _showDialog = MutableLiveData<Boolean>(),
            _countryCode = MutableLiveData<String>(),
            ioDispatcher = DispatchersModule.providerIoDispatcher(),
        )
    }
}
