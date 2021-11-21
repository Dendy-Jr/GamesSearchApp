package com.dendi.android.gamessearchapp.presentation.games

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Retry
import com.dendi.android.gamessearchapp.databinding.FragmentGamesBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment
import com.dendi.android.gamessearchapp.presentation.core.ClickListener
import com.dendi.android.gamessearchapp.presentation.core.navigator
import com.dendi.android.gamessearchapp.presentation.detail.DetailFragment
import com.dendi.android.gamessearchapp.presentation.favorites.FavoritesFragment
import com.dendi.android.gamessearchapp.presentation.games.filter.GamesBottomSheet
import com.dendi.android.gamessearchapp.presentation.games.filter.GenreType
import com.dendi.android.gamessearchapp.presentation.games.filter.ShowAllGames
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesFragment : BaseFragment<GamesViewModel>(), SearchView.OnQueryTextListener, ShowAllGames {

    override fun setRecyclerView() = binding.rvGames
    override fun viewModelClass() = GamesViewModel::class.java
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamesAdapter: GamesAdapter

    private var gamesList: List<GameUi> = listOf()
    private var gameUi: GameUi? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarList)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabFilter.setOnClickListener {
            val gamesBottomSheet: GamesBottomSheet by lazy { GamesBottomSheet(this) }
            gamesBottomSheet.show(parentFragmentManager, "filter")
        }

        gamesAdapter = GamesAdapter(
            object : Retry {
                override fun tryAgain() {
                    viewModel.fetchGames()
                }
            },
            object : ClickListener<Int> {
                override fun click(item: Int) {
                    val fragment = DetailFragment().apply {
                        arguments = bundleOf("id" to item)
                    }
                    navigator().launchFragment(fragment)
                }
            })

        setAdapter(gamesAdapter)
        setupGenreType()
        viewModel.fetchGames()
    }

//    private fun setupObserver() {
//        viewModel.observe(this, { games ->
//            gameUiState = games
//            gamesAdapter.map(gameUiState)
//            scrollTo()
//        })
//    }

    private fun setupGenreType() {
        viewModel.observe(this, { games ->
            gamesList = games
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.readGenreType().collect { genreType ->
                    gamesAdapter.map(gamesList.filter { game ->
                        gameUi = game
                        checkStateGame()

                        when (genreType) {
                            GenreType.MMORPG -> game.matches(GenreType.MMORPG.value)
                            GenreType.SHOOTER -> game.matches(GenreType.SHOOTER.value)
                            GenreType.STRATEGY -> game.matches(GenreType.STRATEGY.value)
                            GenreType.MOBA -> game.matches(GenreType.MOBA.value)
                            GenreType.RACING -> game.matches(GenreType.RACING.value)
                            GenreType.SPORTS -> game.matches(GenreType.SPORTS.value)
                            GenreType.SOCIAL -> game.matches(GenreType.SOCIAL.value)
                            GenreType.CARD -> game.matches(GenreType.CARD.value)
                            GenreType.BATTLE_ROYALE -> game.matches(GenreType.BATTLE_ROYALE.value)
                            GenreType.MMO -> game.matches(GenreType.MMO.value)
                            GenreType.FANTASY -> game.matches(GenreType.FANTASY.value)
                            GenreType.FIGHTING -> game.matches(GenreType.FIGHTING.value)
                            GenreType.ACTION_RPG -> game.matches(GenreType.ACTION_RPG.value)
                            else -> false
                        }

                    })
                }
            }
            scrollTo()
        })
    }

    private fun checkStateGame() {
        progress_games.visibility = View.VISIBLE
        if (gameUi is GameUi.Progress) {
            progress_games.visibility = View.VISIBLE
        } else {
            progress_games.visibility = View.GONE
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
            gamesAdapter.map(games)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(games: List<GameUi>) {
        gamesAdapter.map(gamesList)
    }
}