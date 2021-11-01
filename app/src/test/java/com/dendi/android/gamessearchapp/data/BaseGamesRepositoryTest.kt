package com.dendi.android.gamessearchapp.data

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseGamesRepositoryTest {

    protected class TestToGameMapper : GameDataMapper {
        override fun map(id: Int, thumbnail: String, title: String) = GameData(id, thumbnail, title)
    }
}