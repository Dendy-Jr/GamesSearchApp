package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.SystemRequirementsDb

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class DbSystemRequirementsMapper : Abstract.SystemRequirementsMapper<SystemRequirementsDb> {
    override fun map(
        id: Int,
        graphics: String,
        memory: String,
        os: String,
        processor: String,
        storage: String,
    ) = SystemRequirementsDb(id,
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage)
}