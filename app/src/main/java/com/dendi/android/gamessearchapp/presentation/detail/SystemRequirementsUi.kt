package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class SystemRequirementsUi(
    private val id: Int,
    private val graphics: String,
    private val memory: String,
    private val os: String,
    private val processor: String,
    private val storage: String,
) : Abstract.Object.UnitObject<Abstract.SystemRequirementsMapper<Unit>> {
    override fun map(mapper: Abstract.SystemRequirementsMapper<Unit>) = mapper.map(
        id = id,
        graphics = graphics,
        memory = memory,
        os = os,
        processor = processor,
        storage = storage)
}
