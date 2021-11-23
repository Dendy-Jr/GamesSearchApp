package com.dendi.android.gamessearchapp.presentation.core

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.android.gamessearchapp.R


/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseFragment<T : BaseViewModel<*, *>> : Fragment() {

    protected abstract fun setRecyclerView(): RecyclerView
    protected abstract fun viewModelClass(): Class<T>
    protected lateinit var viewModel: T
    private var hasScrolled = false
    private lateinit var layoutManager: LinearLayoutManager
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = (requireActivity() as BaseActivity).viewModel(viewModelClass(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orientation = requireContext().resources.configuration.orientation
        layoutManager = when {
            setRecyclerView() == view.findViewById(R.id.rvScreenshot) && orientation ==
                    Configuration.ORIENTATION_LANDSCAPE ->
                GridLayoutManager(requireContext(), 2)
            setRecyclerView() == view.findViewById(R.id.rvFavorites ) && orientation ==
                    Configuration.ORIENTATION_PORTRAIT ->
                GridLayoutManager(requireContext(), 2)

            else -> LinearLayoutManager(requireContext())
        }
        recyclerView = setRecyclerView()
        recyclerView?.layoutManager = layoutManager
        recyclerView?.apply {
            setHasFixedSize(true)
            itemAnimator?.changeDuration = 0
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveScrollPosition(layoutManager.findLastCompletelyVisibleItemPosition())
    }

    protected fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView?.adapter = adapter
    }

    private fun itemsCount() = recyclerView?.adapter?.itemCount ?: 0

    protected fun scrollTo(position: Int = viewModel.scrollPosition()) {
        if (itemsCount() > position && position > 0 && !hasScrolled) {
            recyclerView?.smoothScrollToPosition(position)
            hasScrolled = true
        }
    }
}