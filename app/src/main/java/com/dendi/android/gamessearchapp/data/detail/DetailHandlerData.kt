package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.detail.DetailHandlerDomain
import kotlin.Exception

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailHandlerData : Abstract.Object.MapToDomain.HandlerObject {
    data class Success(private val detailData: DetailData) : DetailHandlerData() {
        override fun map(mapper: Abstract.HandlerDomainMapper<DetailHandlerDomain>) =
            mapper.map(detailData)
    }

    data class Fail(private val exception: Exception) : DetailHandlerData() {
        override fun map(mapper: Abstract.HandlerDomainMapper<DetailHandlerDomain>) =
            mapper.map(exception)
    }
}

