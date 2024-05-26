package com.arboleda.sportsapp.di

import com.arboleda.sportsapp.data.endpoints.fixtures.FixturesApi
import com.arboleda.sportsapp.data.repositories.fixtures.FixturesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.fixtures.FixturesRepository
import com.arboleda.sportsapp.domain.usecases.fixtures.FixturesUC
import com.arboleda.sportsapp.domain.usecases.fixtures.GetAllFixturesUC
import com.arboleda.sportsapp.presentation.states.FixturesState
import com.arboleda.sportsapp.presentation.viewmodels.fixtures.FixturesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FixturesModule {
    @Singleton
    @Provides
    fun provideFixturesApi(retrofit: Retrofit): FixturesApi {
        return retrofit.create(FixturesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFixturesRepository(fixturesApi: FixturesApi): FixturesRepository {
        return FixturesRepositoryImpl(fixturesApi = fixturesApi)
    }

    @Singleton
    @Provides
    fun provideGetAllFixtureUC(fixturesRepository: FixturesRepository): GetAllFixturesUC {
        return GetAllFixturesUC(fixturesRepository = fixturesRepository)
    }

    @Singleton
    @Provides
    fun provideFixturesUC(getAllFixturesUC: GetAllFixturesUC): FixturesUC {
        return FixturesUC(getAllFixturesUC = getAllFixturesUC)
    }

    @Singleton
    @Provides
    fun provideFixturesViewModel(fixturesUC: FixturesUC): FixturesViewModel {
        return FixturesViewModel(
            fixturesUC = fixturesUC,
            _fixtures = MutableStateFlow(FixturesState.Loading),
            _showDialog = MutableStateFlow(false),
            ioDispatcher = DispatchersModule.providerIoDispatcher(),
        )
    }
}
