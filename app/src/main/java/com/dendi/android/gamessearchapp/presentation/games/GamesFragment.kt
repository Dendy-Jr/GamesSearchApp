package com.dendi.android.gamessearchapp.presentation.games

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Constants.ID
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.databinding.FragmentGamesBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.navigator
import com.dendi.android.gamessearchapp.presentation.detail.DetailFragment
import com.dendi.android.gamessearchapp.presentation.favorites.FavoritesFragment
import com.dendi.android.gamessearchapp.presentation.games.filter.GamesBottomSheet
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */

class GamesFragment :
    BaseFragment<GamesViewModel, FragmentGamesBinding>(FragmentGamesBinding::inflate),
    SearchView.OnQueryTextListener {

    override fun setRecyclerView() = viewBinding.rvGames
    override fun viewModelClass() = GamesViewModel::class.java
    private lateinit var gamesAdapter: GamesAdapter
    private var category = SHARED_CATEGORY_DEFAULT
    private var sort = SHARED_SORT_DEFAULT

    private lateinit var sharedSort: SharedPreferences
    private lateinit var sharedCategory: SharedPreferences

    private val resourceProvider: ResourceProvider by lazy {
        ResourceProvider.Base(requireContext())
        // TODO: 11/25/2021  
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(viewBinding.toolbarList)

        sharedSort = requireContext().getSharedPreferences(SHARED_PREF_SORT, Context.MODE_PRIVATE)
        sharedCategory =
            requireContext().getSharedPreferences(SHARED_PREF_CATEGORY, Context.MODE_PRIVATE)

        checkConnect()
        viewBinding.fabFilter.setOnClickListener {
            val gamesBottomSheet: GamesBottomSheet by lazy { GamesBottomSheet() }
            gamesBottomSheet.show(parentFragmentManager, GAMES_BOTTOM_SHEET)
        }
        gamesAdapter = GamesAdapter(
            object : Retry {
                override fun tryAgain() {
                    viewModel.fetchGames(category, sort)
                }
            },
            object : ClickListener<Int> {
                override fun click(item: Int) {
                    val fragment = DetailFragment().apply {
                        arguments = bundleOf(ID to item)
                    }
                    navigator().launchFragment(fragment)
                }
            })
        setAdapter(gamesAdapter)
        setupObserver()
        setupGamesCategory()
    }

    private fun setupObserver() {
        viewModel.observe(this, { games ->
            games.map(gamesAdapter)
            scrollTo()
        })
    }

    private fun setupGamesCategory() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.readGamesCategory().collect { gamesCategory ->
                        sharedCategory.edit().putString(CATEGORY, gamesCategory.value).apply()
                    }
                }
                launch {
                    viewModel.readGamesSort().collect { gamesSort ->
                        sharedSort.edit().putString(SORT, gamesSort.sort).apply()
                        category = sharedCategory.getString(CATEGORY, SHARED_CATEGORY_DEFAULT)!!
                        sort = sharedSort.getString(SORT, SHARED_SORT_DEFAULT)!!
                        viewModel.fetchGames(category, sort)
                    }

                }
            }
        }
    }

    private fun checkConnect() {
        if (!viewModel.hasConnect) {
            viewBinding.fabFilter.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.games_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            navigator().launchFragment(FavoritesFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?) = true

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchGame(query)
        }
        return true
    }

    private fun searchGame(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchGame(searchQuery).observe(this, { games ->
            games.map(gamesAdapter)
        })
    }

    companion object {
        private const val SHARED_CATEGORY_DEFAULT = "mmorpg"
        private const val SHARED_SORT_DEFAULT = "alphabetical"
        private const val SHARED_PREF_CATEGORY = "SHARED_PREF_CATEGORY"
        private const val SHARED_PREF_SORT = "SHARED_PREF_SORT"
        private const val GAMES_BOTTOM_SHEET = "GAMES_BOTTOM_SHEET"
        private const val CATEGORY = "CATEGORY"
        private const val SORT = "SORT"
    }
}