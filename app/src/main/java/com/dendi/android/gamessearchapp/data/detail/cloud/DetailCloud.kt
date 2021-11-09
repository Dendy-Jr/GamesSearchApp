package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.DataScreenshotMapper
import com.dendi.android.gamessearchapp.data.detail.DataSystemRequirementsMapper
import com.dendi.android.gamessearchapp.data.detail.DetailData
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class DetailCloud(
    @SerializedName("description")
    private val description: String,
    @SerializedName("developer")
    private val developer: String,
    @SerializedName("freetogame_profile_url")
    private val freetogameProfileUrl: String,
    @SerializedName("game_url")
    private val gameUrl: String,
    @SerializedName("genre")
    private val genre: String,
    @SerializedName("id")
    private val id: Int,
    @SerializedName("minimum_system_requirements")
    private val systemRequirements: SystemRequirementsCloud?,
    @SerializedName("platform")
    private val platform: String,
    @SerializedName("publisher")
    private val publisher: String,
    @SerializedName("release_date")
    private val releaseDate: String,
    @SerializedName("screenshots")
    private val screenshots: List<ScreenshotCloud>?,
    @SerializedName("short_description")
    private val shortDescription: String,
    @SerializedName("status")
    private val status: String,
    @SerializedName("thumbnail")
    private val thumbnail: String,
    @SerializedName("title")
    private val title: String,
) : Abstract.Object.MapToData.DetailObject {
    override fun map(mapper: Abstract.DetailDataMapper<DetailData>) = mapper.map(
        description = description,
        developer = developer,
        freetogameProfileUrl = freetogameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        systemRequirements = systemRequirements?.map(DataSystemRequirementsMapper()),
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots?.map { it.map(DataScreenshotMapper()) },
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title
    )
}
