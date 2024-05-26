package com.arboleda.sportsapp.domain.usecases.leagues

import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import javax.inject.Inject

class GetAllLeaguesUC
    @Inject
    constructor(private val leaguesRepository: LeaguesRepository) {
        suspend operator fun invoke(countryCode: String) = leaguesRepository.getAllLeagues(countryCode)
    }
