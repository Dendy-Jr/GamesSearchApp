package com.dendi.android.gamessearchapp.presentation.games.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.dendi.android.gamessearchapp.core.GamesApp
import com.dendi.android.gamessearchapp.databinding.GamesBottomSheetBinding
import com.dendi.android.gamessearchapp.presentation.games.GamesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

/**
 * @author Dendy-Jr on 18.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var viewModel: GamesViewModel

    private var _binding: GamesBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = GamesBottomSheetBinding.inflate(inflater, container, false)
        viewModel = viewModel(GamesViewModel::class.java, this)
        showGamesCategory()
        showGamesSort()
        binding.applyBtn.setOnClickListener { dismiss() }
        return binding.root
    }

    private fun showGamesSort() {
        binding.gamesSortChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            if (chip?.isChecked == true) {
                with(binding) {
                    when (chip) {
                        releaseDateSort -> viewModel.saveGamesSort(GamesSort.RELEASE_DATE)
                        popularitySort -> viewModel.saveGamesSort(GamesSort.POPULARITY)
                        alphabeticalSort -> viewModel.saveGamesSort(GamesSort.ALPHABETICAL)
                        relevanceSort -> viewModel.saveGamesSort(GamesSort.RELEVANCE)
                        else -> viewModel.saveGamesSort(GamesSort.ALPHABETICAL)
                    }
                }
            }
        }
    }

    private fun showGamesCategory() {
        binding.gamesCategoryChipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            if (chip?.isChecked == true) {

                with(binding) {
                    when (chip) {
                        mmorpg -> viewModel.saveGamesCategory(GamesCategory.MMORPG)
                        shooter -> viewModel.saveGamesCategory(GamesCategory.SHOOTER)
                        strategy -> viewModel.saveGamesCategory(GamesCategory.STRATEGY)
                        moba -> viewModel.saveGamesCategory(GamesCategory.MOBA)
                        racing -> viewModel.saveGamesCategory(GamesCategory.RACING)
                        sports -> viewModel.saveGamesCategory(GamesCategory.SPORTS)
                        social -> viewModel.saveGamesCategory(GamesCategory.SOCIAL)
                        sandbox -> viewModel.saveGamesCategory(GamesCategory.SANDBOX)
                        openWorld -> viewModel.saveGamesCategory(GamesCategory.OPEN_WORLD)
                        survival -> viewModel.saveGamesCategory(GamesCategory.SURVIVAL)
                        pvp -> viewModel.saveGamesCategory(GamesCategory.PVP)
                        pve -> viewModel.saveGamesCategory(GamesCategory.PVE)
                        pixel -> viewModel.saveGamesCategory(GamesCategory.PIXEL)
                        voxel -> viewModel.saveGamesCategory(GamesCategory.VOXEL)
                        zombie -> viewModel.saveGamesCategory(GamesCategory.ZOMBIE)
                        turnBased -> viewModel.saveGamesCategory(GamesCategory.TURN_BASED)
                        firstPerson -> viewModel.saveGamesCategory(GamesCategory.FIRST_PERSON)
                        thirdPerson -> viewModel.saveGamesCategory(GamesCategory.THIRD_PERSON)
                        topDown -> viewModel.saveGamesCategory(GamesCategory.TOP_DOWN)
                        tank -> viewModel.saveGamesCategory(GamesCategory.TANK)
                        space -> viewModel.saveGamesCategory(GamesCategory.SPACE)
                        sailing -> viewModel.saveGamesCategory(GamesCategory.SAILING)
                        sideScroller -> viewModel.saveGamesCategory(GamesCategory.SIDE_SCROLLER)
                        superhero -> viewModel.saveGamesCategory(GamesCategory.SUPERHERO)
                        permadeath -> viewModel.saveGamesCategory(GamesCategory.PERMADEATH)
                        card -> viewModel.saveGamesCategory(GamesCategory.CARD)
                        battleRoyale -> viewModel.saveGamesCategory(GamesCategory.BATTLE_ROYALE)
                        mmo -> viewModel.saveGamesCategory(GamesCategory.MMO)
                        mmofps -> viewModel.saveGamesCategory(GamesCategory.MMOFPS)
                        mmotps -> viewModel.saveGamesCategory(GamesCategory.MMOTPS)
                        threeD -> viewModel.saveGamesCategory(GamesCategory._3D)
                        twoD -> viewModel.saveGamesCategory(GamesCategory._2D)
                        anime -> viewModel.saveGamesCategory(GamesCategory.ANIME)
                        fantasy -> viewModel.saveGamesCategory(GamesCategory.FANTASY)
                        sciFi -> viewModel.saveGamesCategory(GamesCategory.SCI_FI)
                        fighting -> viewModel.saveGamesCategory(GamesCategory.FIGHTING)
                        actionRpg -> viewModel.saveGamesCategory(GamesCategory.ACTION_RPG)
                        action -> viewModel.saveGamesCategory(GamesCategory.ACTION)
                        military -> viewModel.saveGamesCategory(GamesCategory.MILITARY)
                        martialArts -> viewModel.saveGamesCategory(GamesCategory.MARTIAL_ARTS)
                        flight -> viewModel.saveGamesCategory(GamesCategory.FLIGHT)
                        lowSpec -> viewModel.saveGamesCategory(GamesCategory.LOW_SPEC)
                        towerDefense -> viewModel.saveGamesCategory(GamesCategory.TOWER_DEFENSE)
                        horror -> viewModel.saveGamesCategory(GamesCategory.HORROR)
                        mmorts -> viewModel.saveGamesCategory(GamesCategory.MMORTS)
                        else -> viewModel.saveGamesCategory(GamesCategory.MMORPG)
                    }
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
