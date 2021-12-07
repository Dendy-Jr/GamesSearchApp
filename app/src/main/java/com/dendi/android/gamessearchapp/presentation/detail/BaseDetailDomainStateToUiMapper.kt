package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.core.ErrorType
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainStateToUiStateMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainToUiMapper
import com.dendi.android.gamessearchapp.presentation.core.BaseDomainToUiMapper

/**
 * @author Dendy-Jr on 05.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseDetailDomainStateToUiMapper(
    resourceProvider: ResourceProvider,
    private val mapper: DetailDomainToUiMapper<DetailUi>,
) : BaseDomainToUiMapper<DetailDomain, DetailUiState>(resourceProvider),
    DetailDomainStateToUiStateMapper<DetailUiState> {
    override fun map(data: DetailDomain) = DetailUiState(data.map(mapper))

    override fun map(errorType: ErrorType) =
        DetailUiState(DetailUi.Fail(errorMessage(errorType)))

}