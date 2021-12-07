package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

data class SystemRequirementsDomain(
    private val id: Int,
    private val graphics: String,
    private val memory: String,
    private val os: String,
    private val processor: String,
    private val storage: String,
) : Abstract.Mapper.SystemRequirementsObject {
    override fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>) =
        mapper.map(
            id = id,
            graphics = graphics,
            memory = memory,
            os = os,
            processor = processor,
            storage = storage
        )
}
