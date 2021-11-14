package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.data.detail.DetailDataStateToDomainMapper
import com.dendi.android.gamessearchapp.presentation.core.ScrollPosition


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailInteractor : ScrollPosition, ReadById<DetailDomainState> {

    class Base(
        private val detailRepository: DetailRepository,
        private val detailDomainMapper: DetailDataStateToDomainMapper<DetailDomainState>,
    ) : DetailInteractor {

        override suspend fun readId(id: Int) =
            detailRepository.readId(id).map(detailDomainMapper)

    }
}