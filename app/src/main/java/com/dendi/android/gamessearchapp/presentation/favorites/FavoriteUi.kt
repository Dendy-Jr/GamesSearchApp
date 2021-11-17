package com.dendi.android.gamessearchapp.presentation.favorites

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoriteUi {

    fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
        mapper.map(0, "", "", "", "")

    fun map(listener: ClickListener<Int>) = Unit

    object Progress : FavoriteUi

    data class Base(
        private var id: Int,
        private val thumbnail: String,
        private val title: String,
        private val platform: String,
        private val developer: String,
    ) : FavoriteUi {
        override fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                platform = platform,
                developer = developer
            )

        override fun map(listener: ClickListener<Int>) = listener.click(id)
    }
}