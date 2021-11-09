package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.SystemRequirementsDb
import com.dendi.android.gamessearchapp.domain.detail.SystemRequirementsDomain

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class SystemRequirementsData(
    private val id: Int,
    private val graphics: String,
    private val memory: String,
    private val os: String,
    private val processor: String,
    private val storage: String,
) : Abstract.Object.MapToCache.SystemRequirementsObject<SystemRequirementsDb> {
    override fun map(mapper: Abstract.SystemRequirementsMapper<SystemRequirementsDomain>) =
        mapper.map(
            id = id,
            graphics = graphics,
            memory = memory,
            os = os,
            processor = processor,
            storage = storage
        )

    override fun map(mapper: Abstract.SystemRequirementsMapper<SystemRequirementsDb>) =
        mapper.map(
            id = id,
            graphics = graphics,
            memory = memory,
            os = os,
            processor = processor,
            storage = storage

        )

    companion object {
        val EMPTY = SystemRequirementsData(
            0, "", "", "", "", "")
    }
}