package com.dendi.android.gamessearchapp.presentation.games

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.databinding.FailFullscreenBinding
import com.dendi.android.gamessearchapp.databinding.GameLayoutBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class GamesViewHolder(view: View) : BaseViewHolder<GameUi>(view) {

    class Base(private val binding: GameLayoutBinding, private val listener: ClickListener<Int>) :
        BaseViewHolder<GameUi>(binding.root) {

        override fun bind(item: GameUi) {
            item.map(object : GameUiMapper<Unit> {
                override fun map(id: Int, thumbnail: String, title: String) {
                    binding.title.text = title
                    Glide.with(itemView)
                        .load(thumbnail)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.not_found_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.thumbnailIv)
                }

                override fun map(message: String) = Unit
            })
            itemView.setOnClickListener {
                item.map(listener)
            }
        }
    }

    class Error(binding: FailFullscreenBinding, retry: Retry) :
        BaseViewHolder.Error<GameUi>(binding, retry) {

        override fun bind(item: GameUi) {
            item.map(object : GameUiMapper<Unit> {
                override fun map(id: Int, thumbnail: String, title: String) = Unit

                override fun map(message: String) {
                    error.text = message
                }
            })
            button.setOnClickListener {
                retry.tryAgain()
            }
        }
    }
}