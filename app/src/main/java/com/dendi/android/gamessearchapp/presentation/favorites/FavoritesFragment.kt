package com.dendi.android.gamessearchapp.presentation.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.dendi.android.gamessearchapp.databinding.FragmentFavoritesBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.navigator
import com.dendi.android.gamessearchapp.presentation.detail.DetailFragment

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class FavoritesFragment : BaseFragment<FavoritesViewModel>() {

    override fun setRecyclerView() = binding.rvFavorites
    override fun viewModelClass() = FavoritesViewModel::class.java
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarFavorites)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesAdapter = FavoritesAdapter(object : ClickListener<Int> {
            override fun click(item: Int) {
                val fragment = DetailFragment().apply {
                    arguments = bundleOf("id" to item)
                }
                navigator().launchFragment(fragment)
            }
        })
        setAdapter(favoritesAdapter)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.observe(this, { games ->
            games.map(favoritesAdapter)
            scrollTo()
            Log.d("TAG", "FavoritesFragment onViewCreated")
        })
        viewModel.fetchFavorites()
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "FavoritesFragment onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}