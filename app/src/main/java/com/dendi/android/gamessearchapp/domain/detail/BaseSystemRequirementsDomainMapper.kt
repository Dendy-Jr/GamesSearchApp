package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseSystemRequirementsDomainMapper :
    Abstract.SystemRequirementsMapper<SystemRequirementsDomain> {
    override fun map(
        id: Int,
        graphics: String,
        memory: String,
        os: String,
        processor: String,
        storage: String,
    ) = SystemRequirementsDomain(
        id = id,
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage
    )
}