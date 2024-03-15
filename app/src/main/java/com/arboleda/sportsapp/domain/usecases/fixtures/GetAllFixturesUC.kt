package com.arboleda.sportsapp.domain.usecases.fixtures

import com.arboleda.sportsapp.domain.models.fixtures.Fixtures
import com.arboleda.sportsapp.domain.repositories.fixtures.FixturesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFixturesUC
    @Inject
    constructor(private val fixturesRepository: FixturesRepository) {
        operator fun invoke(
            timeZone: String,
            league: Int,
            season: Int,
        ): Flow<Fixtures> {
            return fixturesRepository.getAllFixtures(timeZone, league, season)
        }
    }
