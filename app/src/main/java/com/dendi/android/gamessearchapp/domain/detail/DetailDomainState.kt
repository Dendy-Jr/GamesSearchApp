package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ErrorType


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailDomainState : Abstract.Mapper.DetailDomainStateObject {

    override fun <T> map(mapper: DetailDomainStateToUiStateMapper<T>) =
        mapper.map(DetailDomain.EMPTY)

    data class Success(
        private val detail: DetailDomain,
    ) : DetailDomainState() {
        override fun <T> map(mapper: DetailDomainStateToUiStateMapper<T>) = mapper.map(detail)
    }

    data class Fail(private val errorType: ErrorType) : DetailDomainState() {
        override fun <T> map(mapper: DetailDomainStateToUiStateMapper<T>) = mapper.map(errorType)
    }
}
