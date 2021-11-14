package com.dendi.android.gamessearchapp.presentation.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseAdapter<E, T : BaseViewHolder<E>> :
    RecyclerView.Adapter<T>(), ListMapper<E> {

    protected val list = ArrayList<E>()

    override fun map(data: List<E>) {
        val diffCallback = diffUtilCallback(list, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    abstract fun diffUtilCallback(list: ArrayList<E>, data: List<E>): DiffUtil.Callback

    override fun onBindViewHolder(holder: T, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size
}

interface ClickListener<T> {
    fun click(item: T)
}