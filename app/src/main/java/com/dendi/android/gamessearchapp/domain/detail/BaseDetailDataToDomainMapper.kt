package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.data.detail.DetailData
import com.dendi.android.gamessearchapp.data.detail.DetailDataStateToDomainStateMapper
import com.dendi.android.gamessearchapp.data.detail.DetailDataToDomainMapper
import com.dendi.android.gamessearchapp.domain.core.BaseDataToDomainMapper

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseDetailDataToDomainMapper(
    private val mapper: DetailDataToDomainMapper<DetailDomain>,
) : BaseDataToDomainMapper<DetailData, DetailDomainState>(),
    DetailDataStateToDomainStateMapper<DetailDomainState> {
    override fun map(data: DetailData) = DetailDomainState.Success(data.map(mapper)
    )

    override fun map(e: Exception) = DetailDomainState.Fail(errorType(e))
}