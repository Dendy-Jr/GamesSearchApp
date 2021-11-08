package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailInteractor {

    suspend fun fetchDetail(id: Int): DetailHandlerDomain

    class Base(
        private val detailRepository: DetailRepository,
        private val detailDomainMapper: Abstract.HandlerDomainMapper<DetailHandlerDomain>,
    ) : DetailInteractor {
        override suspend fun fetchDetail(id: Int) =
            detailRepository.fetchDetail(id).map(detailDomainMapper)
    }
}