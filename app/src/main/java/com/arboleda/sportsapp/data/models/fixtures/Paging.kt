package com.arboleda.sportsapp.data.models.fixtures

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.fixtures.Paging as PagingDomain

data class Paging(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int,
) {
    fun toDomain(): PagingDomain {
        return PagingDomain(current = current, total = total)
    }
}
