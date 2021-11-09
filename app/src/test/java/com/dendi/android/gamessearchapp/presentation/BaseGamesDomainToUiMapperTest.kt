package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.games.ErrorType
import com.dendi.android.gamessearchapp.presentation.games.BaseGamesDomainToUiMapper
import com.dendi.android.gamessearchapp.presentation.games.GameUi
import com.dendi.android.gamessearchapp.presentation.games.GamesUi
import org.junit.Assert.*
import org.junit.Test

/**
 * Test for [BaseGamesDomainToUiMapper]
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper =
            BaseGamesDomainToUiMapper(resourceProvider, object : Abstract.GameMapper<GameUi> {
                override fun map(id: Int, thumbnail: String, title: String): GameUi {
                    throw IllegalAccessError("not used here")
                }
            })

        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = GamesUi.Base(listOf(GameUi.Fail("noConnection")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = GamesUi.Base(listOf(GameUi.Fail("serviceUnavailable")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = GamesUi.Base(listOf(GameUi.Fail("somethingWentWrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "noConnection"
            R.string.service_unavailable_message -> "serviceUnavailable"
            else -> "somethingWentWrong"
        }

    }
}