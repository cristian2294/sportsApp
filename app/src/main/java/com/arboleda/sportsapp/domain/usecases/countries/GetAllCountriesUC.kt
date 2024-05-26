package com.arboleda.sportsapp.domain.usecases.countries

import com.arboleda.sportsapp.domain.repositories.countries.CountriesRepository
import javax.inject.Inject

class GetAllCountriesUC
    @Inject
    constructor(private val countriesRepository: CountriesRepository) {
        suspend operator fun invoke() = countriesRepository.getAllCountries()
    }
