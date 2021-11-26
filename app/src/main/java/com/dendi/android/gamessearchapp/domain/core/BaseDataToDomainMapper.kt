package com.dendi.android.gamessearchapp.domain.core

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseDataToDomainMapper<S, R> : Abstract.Mapper.DataToDomain<S, R> {
    protected fun errorType(e: Exception) = when (e) {
        is UnknownHostException -> ErrorType.NO_CONNECTION
        is HttpException -> ErrorType.SERVICE_UNAVAILABLE
        else -> ErrorType.GENERIC_ERROR
    }
}