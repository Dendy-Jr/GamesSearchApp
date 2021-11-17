package com.dendi.android.gamessearchapp.presentation.favorites

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.core.BaseViewHolder
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class FavoritesViewHolder(view: View) : BaseViewHolder<FavoriteUi>(view) {

    class Base(view: View, private val listener: ClickListener<Int>) :
        BaseViewHolder<FavoriteUi>(view) {
        private val titleFavorite: TextView = itemView.findViewById(R.id.title_favorite)
        private val thumbnailFavorite: ImageView = itemView.findViewById(R.id.thumbnail_favorite)
        private val platformFavorite: TextView = itemView.findViewById(R.id.platform_favorite)
        private val developerFavorite: TextView = itemView.findViewById(R.id.developer_favorite)

        override fun bind(item: FavoriteUi) {
            item.map(object : Abstract.FavoriteMapper<Unit> {
                override fun map(
                    id: Int,
                    thumbnail: String,
                    title: String,
                    platform: String,
                    developer: String
                ) {
                    titleFavorite.text = title
                    platformFavorite.text = platform
                    developerFavorite.text = developer
                    Glide.with(itemView)
                        .load(thumbnail)
                        .centerCrop()
                        .placeholder(R.drawable.image_loading)
                        .error(R.drawable.not_found_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(thumbnailFavorite)
                }
            })
            itemView.setOnClickListener {
                item.map(listener)
            }
        }
    }
}