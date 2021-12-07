package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import kotlin.Exception

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class DataDetailState : Abstract.Mapper.DataDetailStateObject {

    override fun <T> map(mapper: DetailDataStateToDomainStateMapper<T>) =
        mapper.map(DetailData.EMPTY)

    data class Success(private val detailData: DetailData) : DataDetailState() {
        override fun <T> map(mapper: DetailDataStateToDomainStateMapper<T>) =
            mapper.map(detailData)
    }

    data class Fail(private val exception: Exception) : DataDetailState() {
        override fun <T> map(mapper: DetailDataStateToDomainStateMapper<T>) =
            mapper.map(exception)
    }
}


