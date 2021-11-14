package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.ErrorType


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailDomainState {

    abstract fun <T> map(mapper: DetailDomainStateToUiMapper<T>): T

    data class Success(
        private val detail: DetailDomain,
    ) : DetailDomainState() {
        override fun <T> map(mapper: DetailDomainStateToUiMapper<T>) = mapper.map(detail)
    }

    data class Fail(private val errorType: ErrorType) : DetailDomainState() {
        override fun <T> map(mapper: DetailDomainStateToUiMapper<T>) = mapper.map(errorType)
    }
}
