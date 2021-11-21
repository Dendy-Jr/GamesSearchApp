package com.dendi.android.gamessearchapp.presentation.games

import android.view.ViewGroup
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.presentation.core.BaseAdapter
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.DiffUtilCallback

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesAdapter(
    private val retry: Retry,
    private val listener: ClickListener<Int>,
) : BaseAdapter<GameUi, BaseViewHolder<GameUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is GameUi.Base -> 0
        is GameUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> GamesViewHolder.Base(R.layout.game_item.makeView(parent), listener)
        1 -> GamesViewHolder.Error(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    override fun diffUtilCallback(list: ArrayList<GameUi>, data: List<GameUi>) =
        GamesDiffUtilCallback(list, data)

    inner class GamesDiffUtilCallback(
        oldList: List<GameUi>,
        newList: List<GameUi>,
    ) : DiffUtilCallback<GameUi>(oldList, newList)
}
