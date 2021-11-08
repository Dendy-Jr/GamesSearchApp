package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.DetailData
import com.dendi.android.gamessearchapp.domain.games.ErrorType
import com.dendi.android.gamessearchapp.presentation.detail.DetailHandlerUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailHandlerDomain : Abstract.Object.Ui.HandlerObject {
    data class Success(
        private val detail: DetailData,
        private val domainMapper: Abstract.DetailDomainMapper<DetailDomain>,
    ) : DetailHandlerDomain() {
        override fun map(mapper: Abstract.HandlerUiMapper<DetailHandlerUi>) =
            mapper.map(detail.map(domainMapper))
    }

    data class Fail(private val exception: Exception) : DetailHandlerDomain() {
        override fun map(mapper: Abstract.HandlerUiMapper<DetailHandlerUi>) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
