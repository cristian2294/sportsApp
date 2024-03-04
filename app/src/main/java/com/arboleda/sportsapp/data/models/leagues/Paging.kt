package com.arboleda.sportsapp.data.models.leagues

import com.google.gson.annotations.SerializedName
import com.arboleda.sportsapp.domain.models.leagues.Paging as PagingDomain

data class Paging(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int,
) {
    fun toDomain(): PagingDomain =
        PagingDomain(
            current = current,
            total = total,
        )
}
