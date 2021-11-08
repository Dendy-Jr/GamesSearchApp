package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.SystemRequirementsData
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class SystemRequirementsCloud(
    @SerializedName("graphics")
    val graphics: String?,
    @SerializedName("memory")
    val memory: String?,
    @SerializedName("os")
    val os: String?,
    @SerializedName("processor")
    val processor: String?,
    @SerializedName("storage")
    val storage: String?,
) : Abstract.Object.Data.SystemRequirementsObject {
    override fun map(mapper: Abstract.SystemRequirementsMapper<SystemRequirementsData>) =
        mapper.map(
            0, graphics = graphics ?: "",
            memory = memory ?: "",
            os = os ?: "",
            processor = processor ?: "",
            storage = storage ?: ""
        )
}
