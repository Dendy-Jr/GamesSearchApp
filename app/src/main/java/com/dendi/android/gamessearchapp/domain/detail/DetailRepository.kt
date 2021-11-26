package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.data.core.BaseRepository
import com.dendi.android.gamessearchapp.data.detail.DataDetailState
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailRepository : BaseRepository ,ReadById<DataDetailState>{
    suspend fun saveToFavorite(game: FavoriteCache.Base)

    suspend fun deleteFromFavorite(game: FavoriteCache.Base)
}