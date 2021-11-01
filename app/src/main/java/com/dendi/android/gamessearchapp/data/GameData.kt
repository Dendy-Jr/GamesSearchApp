package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.cache.GameDb

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameData(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object<GameDb, Abstract.Mapper.Empty>
//todo fix