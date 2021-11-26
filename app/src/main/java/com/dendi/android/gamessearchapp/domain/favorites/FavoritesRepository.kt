package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Show
import com.dendi.android.gamessearchapp.data.core.BaseRepository
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesRepository : BaseRepository ,Show<List<FavoriteCache>>