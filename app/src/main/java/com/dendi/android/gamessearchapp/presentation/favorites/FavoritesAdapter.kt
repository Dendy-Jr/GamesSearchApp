package com.dendi.android.gamessearchapp.presentation.favorites

import android.view.ViewGroup
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.presentation.core.BaseAdapter
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.DiffUtilCallback

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class FavoritesAdapter(
    private val listener: ClickListener<Int>,
) : BaseAdapter<FavoriteUi, BaseViewHolder<FavoriteUi>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      FavoritesViewHolder.Base(R.layout.favorite_item.makeView(parent), listener)

    override fun diffUtilCallback(list: ArrayList<FavoriteUi>, data: List<FavoriteUi>) =
        FavoritesDiffUtilCallback(list, data)

    inner class FavoritesDiffUtilCallback(
        oldList: List<FavoriteUi>,
        newList: List<FavoriteUi>,
    ) : DiffUtilCallback<FavoriteUi>(oldList, newList)
}