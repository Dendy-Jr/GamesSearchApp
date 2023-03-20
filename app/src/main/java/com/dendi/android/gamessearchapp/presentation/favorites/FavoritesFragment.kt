package com.dendi.android.gamessearchapp.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.dendi.android.gamessearchapp.databinding.FragmentFavoritesBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.core.ClickListener

class FavoritesFragment :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    override fun setRecyclerView() = viewBinding.rvFavorites
    override fun viewModelClass() = FavoritesViewModel::class.java
    private lateinit var favoritesAdapter: FavoritesAdapter

    private var favoritesList: List<FavoriteUi> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(viewBinding.toolbarFavorites)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        favoritesAdapter = FavoritesAdapter(object : ClickListener<Int> {
            override fun click(item: Int) {
                val directions =
                    FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(item)
                findNavController().navigate(directions)
            }
        })
        setAdapter(favoritesAdapter)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.observe(this) { games ->
            favoritesList = games
            checkProgress()
            favoritesAdapter.map(games)
            scrollTo()
        }
        viewModel.fetchFavorites()
    }

    private fun checkProgress() = with(viewBinding) {
        favoritesList.map { game ->
            if (game is FavoriteUi.Progress) {
                progressFavorites.root.visibility = View.VISIBLE
            } else {
                progressFavorites.root.visibility = View.GONE
            }
        }
    }
}