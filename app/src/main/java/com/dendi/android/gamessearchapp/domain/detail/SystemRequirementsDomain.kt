package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.detail.SystemRequirementsUi


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
) : Abstract.Object.MapToUi.SystemRequirementsObject {
    override fun map(mapper: Abstract.SystemRequirementsMapper<SystemRequirementsUi>) =
        mapper.map(
            id = id,
            graphics = graphics,
            memory = memory,
            os = os,
            processor = processor,
            storage = storage
        )
}