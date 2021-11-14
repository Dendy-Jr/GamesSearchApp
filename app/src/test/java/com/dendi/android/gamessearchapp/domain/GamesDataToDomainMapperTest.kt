package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ErrorType
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.domain.games.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesDataToDomainMapperTest {

    private val mapper = BaseGamesDataStateToDomainMapper(object : Abstract.ToGameMapper<GameDomain> {
        override fun map(id: Int, thumbnail: String, title: String) =
            GameDomain.Base(id = id, thumbnail = thumbnail, title = title)
    })

    @Test
    fun test_success() {
        val actual = mapper.map(
            listOf(
                GameData.Base(1, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData.Base(82, "https://www.freetogame.com/g/82/thumbnail.jpg", "Line of Sight")
            )
        )

        val data = listOf(
            GameDomain.Base(1, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
            GameDomain.Base(82, "https://www.freetogame.com/g/82/thumbnail.jpg", "Line of Sight")
        )

        val expected = GamesDomainState.Success(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var actual = mapper.map(UnknownHostException())
        var expected = GamesDomainState.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalAccessException())
        expected = GamesDomainState.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }
}