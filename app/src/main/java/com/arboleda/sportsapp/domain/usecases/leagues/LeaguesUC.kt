package com.arboleda.sportsapp.domain.usecases.leagues

import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import javax.inject.Inject

class LeaguesUC @Inject constructor(private val leaguesRepository: LeaguesRepository) {
    suspend operator fun invoke(countryCode: String) =
        leaguesRepository.getAllLeagues(countryCode)
}
