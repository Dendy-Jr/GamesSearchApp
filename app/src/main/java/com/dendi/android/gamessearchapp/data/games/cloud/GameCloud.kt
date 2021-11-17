package com.dendi.android.gamessearchapp.data.games.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */


interface GameCloud : Abstract.CloudObject {

    fun <T> map(mapper: Abstract.ToGameMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("thumbnail")
        private val thumbnail: String,
        @SerializedName("title")
        private val title: String,
        @SerializedName("genre")
        private val genre: String,
        @SerializedName("release_date")
        private val releaseDate: String,
    ) : GameCloud {
        override fun <T> map(mapper: Abstract.ToGameMapper<T>) =
            mapper.map(
                id,
                thumbnail = thumbnail,
                title = title,
                genre = genre,
                releaseDate = releaseDate
            )
    }
}
