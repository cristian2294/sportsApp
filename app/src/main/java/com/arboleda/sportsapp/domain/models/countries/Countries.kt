package com.arboleda.sportsapp.domain.models.countries

import com.arboleda.sportsapp.data.models.countries.Paging
import com.arboleda.sportsapp.data.models.countries.Parameters
import com.arboleda.sportsapp.data.models.countries.Response

data class Countries(
    val errors: List<Any>,
    val get: String,
    val paging: Paging,
    val parameters: List<Parameters>,
    val response: List<Response>,
    val results: Int,
)
