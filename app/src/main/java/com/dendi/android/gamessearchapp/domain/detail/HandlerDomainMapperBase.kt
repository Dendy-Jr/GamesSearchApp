package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.DetailData

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class HandlerDomainMapperBase(
    private val domainMapper: Abstract.DetailDomainMapper<DetailDomain>,
) : Abstract.HandlerDomainMapper<DetailHandlerDomain> {
    override fun map(detail: DetailData) = DetailHandlerDomain.Success(
        detail, domainMapper
    )

    override fun map(exception: Exception) = DetailHandlerDomain.Fail(exception)
}