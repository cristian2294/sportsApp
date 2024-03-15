package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Periods as PeriodsDomain

data class Periods(
    @SerializedName("first")
    val first: Int,
    @SerializedName("second")
    val second: Any?,
) {
    fun toDomain(): PeriodsDomain {
        return PeriodsDomain(
            first = first,
            second = second,
        )
    }
}
