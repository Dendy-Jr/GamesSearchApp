package com.dendi.android.gamessearchapp.presentation.core

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseViewHolder<E>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: E) {}

    class FullScreenProgress<E>(view: View) :
        BaseViewHolder<E>(view)

    abstract class Fail<E>(view: View, protected val retry: Retry) :
        BaseViewHolder<E>(view) {
        protected val error: TextView = itemView.findViewById(R.id.messageError)
        protected val button:Button = itemView.findViewById(R.id.tryAgainButton)
    }
}