package com.arboleda.sportsapp.di

import androidx.lifecycle.MutableLiveData
import com.arboleda.sportsapp.data.endpoints.leagues.LeaguesApi
import com.arboleda.sportsapp.data.repositories.leagues.LeaguesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import com.arboleda.sportsapp.domain.repositories.preferences.DatastorePreferencesRepository
import com.arboleda.sportsapp.domain.usecases.leagues.GetAllLeaguesUC
import com.arboleda.sportsapp.domain.usecases.leagues.GetLeagueIdUC
import com.arboleda.sportsapp.domain.usecases.leagues.LeaguesUC
import com.arboleda.sportsapp.domain.usecases.leagues.SetLeagueIdUC
import com.arboleda.sportsapp.presentation.states.LeagueState
import com.arboleda.sportsapp.presentation.viewmodels.leagues.LeaguesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LeaguesModule {
    @Provides
    @Singleton
    fun provideLeaguesApi(retrofit: Retrofit): LeaguesApi {
        return retrofit.create(LeaguesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLeaguesRepository(
        datastorePreferencesRepository: DatastorePreferencesRepository,
        leaguesApi: LeaguesApi,
    ): LeaguesRepository =
        LeaguesRepositoryImpl(
            datastorePreferencesRepository = datastorePreferencesRepository,
            leaguesApi = leaguesApi,
        )

    @Provides
    @Singleton
    fun provideGetAllLeagues(leaguesRepository: LeaguesRepository): GetAllLeaguesUC = GetAllLeaguesUC(leaguesRepository = leaguesRepository)

    @Provides
    @Singleton
    fun provideGetLeagueIdUC(leaguesRepository: LeaguesRepository): GetLeagueIdUC = GetLeagueIdUC(leaguesRepository = leaguesRepository)

    @Provides
    @Singleton
    fun provideSetLeagueIdUC(leaguesRepository: LeaguesRepository): SetLeagueIdUC = SetLeagueIdUC(leaguesRepository = leaguesRepository)

    @Provides
    @Singleton
    fun provideLeaguesUC(
        getAllLeaguesUC: GetAllLeaguesUC,
        getLeagueIdUC: GetLeagueIdUC,
        setLeagueIdUC: SetLeagueIdUC,
    ): LeaguesUC =
        LeaguesUC(
            getAllLeaguesUC = getAllLeaguesUC,
            getLeagueIdUC = getLeagueIdUC,
            setLeagueIdUC = setLeagueIdUC,
        )

    @Provides
    @Singleton
    fun provideLeaguesViewModel(leaguesUC: LeaguesUC): LeaguesViewModel =
        LeaguesViewModel(
            leaguesUC = leaguesUC,
            _leagueState = MutableLiveData<LeagueState>(),
            _showDialog = MutableLiveData<Boolean>(),
            _leagueId = MutableLiveData<Int>(),
            ioDispatcher = DispatchersModule.providerIoDispatcher(),
        )
}
