package com.arboleda.sportsapp.domain.usecases.countries

data class CountriesUC(
    val getAllCountriesUC: GetAllCountriesUC,
    val getCountryCodeUC: GetCountryCodeUC,
    val setCountryCode: SetCountryCodeUC,
)
