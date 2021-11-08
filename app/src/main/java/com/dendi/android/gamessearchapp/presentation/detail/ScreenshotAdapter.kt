package com.dendi.android.gamessearchapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.databinding.ScreenshotListBinding
import com.dendi.android.gamessearchapp.presentation.core.DiffUtilCallback

/**
 * @author Dendy-Jr on 08.11.2021
 * olehvynnytskyi@gmail.com
 */
class ScreenshotAdapter: RecyclerView.Adapter<ScreenshotAdapter.ScreenshotViewHolder>() {

    private val screenshotList = ArrayList<ScreenshotUi>()

    fun update(new: List<ScreenshotUi>) {
        val diffCallback = DiffUtilCallback(screenshotList, new)
        val result = DiffUtil.calculateDiff(diffCallback)
        screenshotList.clear()
        screenshotList.addAll(new)
        result.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotViewHolder {
        val binding =
            ScreenshotListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScreenshotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScreenshotViewHolder, position: Int) {
        holder.bind(screenshotList[position])
    }

    override fun getItemCount() = screenshotList.size

    inner class ScreenshotViewHolder(private val binding: ScreenshotListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(screenshot: ScreenshotUi) {
            screenshot.map(object : Abstract.ScreenshotMapper<Unit> {
                override fun map(id: Int, image: String) {
                    Glide.with(itemView)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.not_found_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.screenshotIv)
                }
            })
        }
    }


}