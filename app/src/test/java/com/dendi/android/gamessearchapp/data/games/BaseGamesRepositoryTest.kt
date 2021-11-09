package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseGamesRepositoryTest {

    protected class TestToGameMapper : Abstract.GameMapper<GameData> {
        override fun map(id: Int, thumbnail: String, title: String) = GameData(id, thumbnail, title)
    }
}