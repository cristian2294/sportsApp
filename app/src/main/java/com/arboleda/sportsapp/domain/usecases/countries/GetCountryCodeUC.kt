package com.arboleda.sportsapp.domain.usecases.countries

import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import javax.inject.Inject

class GetCountryCodeUC
    @Inject
    constructor(private val countriesRepository: CountriesRepository) {
        suspend operator fun invoke(key: String): String? = countriesRepository.getCountryCode(key)
    }
