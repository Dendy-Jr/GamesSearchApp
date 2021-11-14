package com.dendi.android.gamessearchapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.databinding.ScreenshotListBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseAdapter
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.DiffUtilCallback


/**
 * @author Dendy-Jr on 08.11.2021
 * olehvynnytskyi@gmail.com
 */
class ScreenshotsAdapter : BaseAdapter<ScreenshotUi, BaseViewHolder<ScreenshotUi>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = ScreenshotViewHolder.Base(ScreenshotListBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))

    override fun diffUtilCallback(
        list: ArrayList<ScreenshotUi>,
        data: List<ScreenshotUi>,
    ) = ScreenshotsDiffUtilCallback(list, data)

    inner class ScreenshotsDiffUtilCallback(
        oldList: List<ScreenshotUi>,
        newList: List<ScreenshotUi>,
    ) : DiffUtilCallback<ScreenshotUi>(oldList, newList)
}