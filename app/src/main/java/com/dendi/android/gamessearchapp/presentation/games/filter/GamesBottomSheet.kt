package com.dendi.android.gamessearchapp.presentation.games.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.dendi.android.gamessearchapp.core.GamesApp
import com.dendi.android.gamessearchapp.databinding.GamesBottomSheetBinding
import com.dendi.android.gamessearchapp.presentation.games.GameUi
import com.dendi.android.gamessearchapp.presentation.games.GamesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

/**
 * @author Dendy-Jr on 18.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesBottomSheet(private val showAllGames: ShowAllGames) : BottomSheetDialogFragment() {

    private lateinit var viewModel: GamesViewModel

    private val games: List<GameUi> = listOf()
    private var _binding: GamesBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GamesBottomSheetBinding.inflate(inflater, container, false)
        viewModel = viewModel(GamesViewModel::class.java, this)
        showChipsCategory()
        return binding.root
    }

    private fun showChipsCategory() {
        binding.genreChipsGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            if (chip?.isChecked == true) {

                with(binding) {
                    when (chip) {
                        all -> showAllGames.onClick(games)
                        mmorpg -> viewModel.saveGenreType(GenreType.MMORPG)
                        shooter -> viewModel.saveGenreType(GenreType.SHOOTER)
                        strategy -> viewModel.saveGenreType(GenreType.STRATEGY)
                        moba -> viewModel.saveGenreType(GenreType.MOBA)
                        racing -> viewModel.saveGenreType(GenreType.RACING)
                        sports -> viewModel.saveGenreType(GenreType.SPORTS)
                        social -> viewModel.saveGenreType(GenreType.SOCIAL)
                        card -> viewModel.saveGenreType(GenreType.CARD)
                        battleRoyale -> viewModel.saveGenreType(GenreType.BATTLE_ROYALE)
                        mmo -> viewModel.saveGenreType(GenreType.MMO)
                        fantasy -> viewModel.saveGenreType(GenreType.FANTASY)
                        actionRpg -> viewModel.saveGenreType(GenreType.ACTION_RPG)
                        fighting -> viewModel.saveGenreType(GenreType.FIGHTING)
                    }
                    dismiss()
                }
            }
        }
    }

    private fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner) =
        (activity?.application as GamesApp).viewModel(clazz, owner)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface ShowAllGames {
    fun onClick(games: List<GameUi>)
}