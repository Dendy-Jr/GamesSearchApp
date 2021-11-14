package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface SystemRequirementsData : Abstract.DataObject {

    fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>): T
    fun <T : Abstract.CacheObject> map(mapper: Abstract.SystemRequirementsMapper<T>): T

    data class Base(
        private val id: Int,
        private val graphics: String,
        private val memory: String,
        private val os: String,
        private val processor: String,
        private val storage: String,
    ) : SystemRequirementsData {
        override fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>) =
            mapper.map(
                id = id,
                graphics = graphics,
                memory = memory,
                os = os,
                processor = processor,
                storage = storage
            )

        override fun <T : Abstract.CacheObject> map(mapper: Abstract.SystemRequirementsMapper<T>) =
            mapper.map(
                id = id,
                graphics = graphics,
                memory = memory,
                os = os,
                processor = processor,
                storage = storage
            )

        companion object {
            val EMPTY = Base(
                0, "", "", "", "", "")
        }
    }
}
