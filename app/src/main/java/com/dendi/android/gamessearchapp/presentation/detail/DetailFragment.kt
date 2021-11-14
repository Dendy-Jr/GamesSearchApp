package com.dendi.android.gamessearchapp.presentation.detail


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.databinding.FragmentDetailBinding
import com.dendi.android.gamessearchapp.presentation.core.BaseFragment

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailFragment : BaseFragment<DetailViewModel>() {

    override fun setRecyclerView() = binding.rvDetail
    override fun viewModelClass() = DetailViewModel::class.java
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var screenshotAdapter: ScreenshotsAdapter

    private lateinit var sharedScroll: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarDetail)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        sharedScroll = requireContext().getSharedPreferences("scroll", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenshotAdapter = ScreenshotsAdapter()
        var id = 0
        val bundle: Bundle? = this.arguments
        if (arguments != null) {
            val detailId = bundle!!.getInt("id")
            id = detailId
        }

        setupObserve()
        viewModel.fetchDetail(id)
    }

    private fun setupObserve() {
        viewModel.observe(this, { detail ->
            detail.map(object : DetailUiMapper<Unit> {
                override fun map(
                    description: String,
                    developer: String,
                    freetogameProfileUrl: String,
                    gameUrl: String,
                    genre: String,
                    id: Int,
                    systemRequirements: SystemRequirementsUi.Base,
                    platform: String,
                    publisher: String,
                    releaseDate: String,
                    screenshots: List<ScreenshotUi.Base>,
                    shortDescription: String,
                    status: String,
                    thumbnail: String,
                    title: String,
                ) {
                    with(binding) {
                        toolbarDetail.title = title

                        Glide.with(requireActivity())
                            .load(thumbnail)
                            .centerCrop()
                            .placeholder(R.drawable.image_loading)
                            .error(R.drawable.not_found_image)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivThumbnail)

                        setAdapter(screenshotAdapter)

                        ScreenshotsUi.Base(screenshots).map(screenshotAdapter)

                        descriptionTextView.text = description
                        tvStrategy.text = genre
                        tvPlatform.text = platform
                        tvPublisher.text = publisher
                        tvDeveloper.text = developer
                        tvReleaseDate.text = releaseDate

                        systemRequirements.map(object : Abstract.SystemRequirementsMapper<Unit> {
                            override fun map(
                                id: Int,
                                graphics: String,
                                memory: String,
                                os: String,
                                processor: String,
                                storage: String,
                            ) {
                                tvOs.text = os
                                tvProcessor.text = processor
                                tvMemory.text = memory
                                tvGraphics.text = graphics
                                tvStorage.text = storage

                            }
                        })
                    }
                }

                override fun map(message: String) = Unit
            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}







