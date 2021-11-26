package com.dendi.android.gamessearchapp.presentation.core

import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ErrorType
import com.dendi.android.gamessearchapp.core.ResourceProvider

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseDomainToUiMapper<S, T>(
    private val resourceProvider: ResourceProvider,
) : Abstract.Mapper.DomainToUi<S, T> {

    protected fun errorMessage(errorType: ErrorType) = resourceProvider.getString(
        when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
    )
}