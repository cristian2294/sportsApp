package com.arboleda.sportsapp.domain.usecases.leagues

import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import javax.inject.Inject

class SetLeagueIdUC
    @Inject
    constructor(private val leaguesRepository: LeaguesRepository) {
        suspend operator fun invoke(
            key: String,
            value: Int,
        ) = leaguesRepository.setLeagueId(key, value)
    }
