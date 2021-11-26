package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface SystemRequirementsCloud : Abstract.CloudObject {

    fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>): T

    class Base(
        @SerializedName("graphics")
        private val graphics: String?,
        @SerializedName("memory")
        private val memory: String?,
        @SerializedName("os")
        private val os: String?,
        @SerializedName("processor")
        private val processor: String?,
        @SerializedName("storage")
        private val storage: String?,
    ) : SystemRequirementsCloud {
        override fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>) =
            mapper.map(
                0, graphics = graphics ?: "",
                memory = memory ?: "",
                os = os ?: "",
                processor = processor ?: "",
                storage = storage ?: ""
            )
    }
}

