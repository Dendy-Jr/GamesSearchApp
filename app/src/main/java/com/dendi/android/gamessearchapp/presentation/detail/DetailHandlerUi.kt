package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.domain.games.ErrorType

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DetailHandlerUi : Abstract.Object.UnitObject<DetailCommunication> {

    class Success(
        private val detail: DetailDomain,
        private val detailMapper: Abstract.DetailUiMapper<DetailUi>,
    ) : DetailHandlerUi() {
        override fun map(mapper: DetailCommunication) = mapper.map(detail.map(detailMapper))
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : DetailHandlerUi() {
        override fun map(mapper: DetailCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(DetailUi.Fail(message))
        }
    }
}
