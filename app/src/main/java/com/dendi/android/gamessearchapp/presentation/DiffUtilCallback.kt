package com.dendi.android.gamessearchapp.presentation

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class DiffUtilCallback(
    private val oldList: List<GameUi>,
    private val newList: List<GameUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItemPosition == newItemPosition
}