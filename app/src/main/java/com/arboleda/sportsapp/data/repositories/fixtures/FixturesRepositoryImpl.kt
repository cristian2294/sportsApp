package com.arboleda.sportsapp.data.repositories.fixtures

import com.arboleda.sportsapp.data.endpoints.fixtures.FixturesApi
import com.arboleda.sportsapp.domain.models.fixtures.Fixtures
import com.arboleda.sportsapp.domain.repositories.fixtures.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FixturesRepositoryImpl
    @Inject
    constructor(private val fixturesApi: FixturesApi) : FixturesRepository {
        override fun getAllFixtures(
            timeZone: String,
            league: Int,
            season: Int,
        ): Flow<Fixtures> {
            return flow {
                val fixturesDomain =
                    fixturesApi.getAllFixtures(timeZone, league, season).body()?.toDomain()
                        ?: throw IllegalStateException("Response body is null")
                emit(fixturesDomain)
            }
        }
    }
