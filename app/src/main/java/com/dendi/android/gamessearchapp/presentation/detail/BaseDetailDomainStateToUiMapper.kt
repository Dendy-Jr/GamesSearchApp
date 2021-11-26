package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.core.ErrorType
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainToUiMapper
import com.dendi.android.gamessearchapp.presentation.core.BaseDomainToUiMapper

/**
 * @author Dendy-Jr on 05.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseDetailDomainStateToUiMapper(
    resourceProvider: ResourceProvider,
    private val mapper: DetailDomainToUiMapper<DetailUi.Base>,
) : BaseDomainToUiMapper<DetailDomain, DetailUiState>(resourceProvider),
    DetailDomainStateToUiMapper<DetailUiState> {
    override fun map(data: DetailDomain) = DetailUiState.Base(data.map(mapper))

    override fun map(errorType: ErrorType) =
        DetailUiState.Base(DetailUi.Fail(errorMessage(errorType)))

}