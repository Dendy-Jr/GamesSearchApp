package com.dendi.android.gamessearchapp.presentation.core

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dendi.android.gamessearchapp.R


/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */

typealias Inflate<S> = (LayoutInflater, ViewGroup?, Boolean) -> S

abstract class BaseFragment<T : BaseViewModel<*, *>, VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    protected abstract fun setRecyclerView(): RecyclerView
    protected abstract fun viewModelClass(): Class<T>
    protected lateinit var viewModel: T
    private var hasScrolled = false
    private lateinit var layoutManager: LinearLayoutManager
    private var recyclerView: RecyclerView? = null

    private var _viewBinding: VB? = null
    val viewBinding get() = _viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = (requireActivity() as BaseActivity).viewModel(viewModelClass(), this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _viewBinding = inflate.invoke(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orientation = requireContext().resources.configuration.orientation
        layoutManager = when {
            setRecyclerView() == view.findViewById(R.id.rvScreenshot) && orientation ==
                    Configuration.ORIENTATION_LANDSCAPE ->
                GridLayoutManager(requireContext(), 2)
            setRecyclerView() == view.findViewById(R.id.rvFavorites) && orientation ==
                    Configuration.ORIENTATION_PORTRAIT ->
                GridLayoutManager(requireContext(), 2)
            setRecyclerView() == view.findViewById(R.id.rvFavorites) && orientation ==
                    Configuration.ORIENTATION_LANDSCAPE ->
                GridLayoutManager(requireContext(), 3)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}