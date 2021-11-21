package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Matcher
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

interface GameUi : Matcher<String> {

    fun <T> map(mapper: GameUiMapper<T>) =
        mapper.map(0, "", "", "", "")

    fun map(listener: ClickListener<Int>) = Unit

    override fun matches(arg: String) = false

    object Progress : GameUi

    data class Base(
        private var id: Int,
        private val thumbnail: String,
        private val title: String,
        private val genre: String,
        private val releaseDate: String,
    ) : GameUi {
        override fun <T> map(mapper: GameUiMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                genre = genre,
                releaseDate = releaseDate
            )

        override fun map(listener: ClickListener<Int>) = listener.click(id)

        override fun matches(arg: String) = arg == genre
    }

    data class Fail(private val message: String) : GameUi {
        override fun <T> map(mapper: GameUiMapper<T>) =
            mapper.map(message)
    }
}

