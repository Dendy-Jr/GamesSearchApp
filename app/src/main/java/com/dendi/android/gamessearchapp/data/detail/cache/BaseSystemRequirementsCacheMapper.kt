package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseSystemRequirementsCacheMapper :
    Abstract.SystemRequirementsMapper<SystemRequirementsCache> {
    override fun map(
        id: Int,
        graphics: String,
        memory: String,
        os: String,
        processor: String,
        storage: String,
    ) = SystemRequirementsCache(id,
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage)
}