package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.domain.games.ErrorType

/**
 * @author Dendy-Jr on 05.11.2021
 * olehvynnytskyi@gmail.com
 */
class HandlerUiMapperBase(
    private val mapper: Abstract.DetailUiMapper<DetailUi>,
    private val resourceProvider: ResourceProvider,
) : Abstract.HandlerUiMapper<DetailHandlerUi> {
    override fun map(detail: DetailDomain) = DetailHandlerUi.Success(detail, mapper)

    override fun map(errorType: ErrorType) = DetailHandlerUi.Fail(errorType, resourceProvider)

}