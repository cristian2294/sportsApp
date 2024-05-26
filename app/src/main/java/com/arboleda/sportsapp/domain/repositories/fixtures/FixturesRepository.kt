package com.arboleda.sportsapp.domain.repositories.fixtures

import com.arboleda.sportsapp.domain.models.fixtures.Fixtures
import kotlinx.coroutines.flow.Flow

interface FixturesRepository {
    fun getAllFixtures(
        timeZone: String,
        league: Int,
        season: Int,
    ): Flow<Fixtures>
}
