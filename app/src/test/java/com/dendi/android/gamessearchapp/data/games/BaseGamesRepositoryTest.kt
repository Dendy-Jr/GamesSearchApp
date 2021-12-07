package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameCache

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseGamesRepositoryTest {

    protected class TestToGameDataMapper : Abstract.GameMapper<GameData> {
        override fun map(id: Int, thumbnail: String, title: String, shortDescription: String) =
            GameData(id, thumbnail, title, shortDescription)
    }

    protected class TestToGameCacheMapper : Abstract.GameMapper<GameCache> {
        override fun map(id: Int, thumbnail: String, title: String, shortDescription: String) =
            GameCache(id, thumbnail, title, shortDescription)
    }
}