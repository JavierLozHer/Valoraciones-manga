package edu.iesam.valoracionesmanga.features.manga.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.core.presentation.visible
import edu.iesam.valoracionesmanga.databinding.FragmentMangaDetailBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaDetailFragment: Fragment() {

    private var _binding: FragmentMangaDetailBinding? = null
    private val binding get() = _binding!!

    private val mangaArgs: MangaDetailFragmentArgs by navArgs()

    private val viewModel : MangaDetailViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = binding.mangaToolbar.toolbar
        toolbar.title = mangaArgs.mangaTitle

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        setupObserver()
        setupTab()
        getManga()

    }

    private fun getManga() {
        viewModel.getManga(mangaArgs.mangaId)
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.manga, uiState.score)
            bindDataAssessment(uiState.assessment)
            showError(uiState.errorApp)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visible()
        } else {
            binding.progressBar.hide()
        }
    }

    private fun bindData(manga: Manga?, score: Double?) {
        manga?.let {
            binding.mangaToolbar.toolbar.title = manga.title
            binding.mangaDetailInfoView.render(manga, score)
            binding.buttonAssess.setOnClickListener {
                findNavController().navigate(MangaDetailFragmentDirections.actionMangaDetailToAssessmentForm(manga.id, manga.img))
            }
        }
    }

    private fun bindDataAssessment(assessments: List<AssessmentManga>?) {
        assessments?.let {
            binding.mangaDetailAssessmentView.render(assessments)
        }
    }


    private fun showError(errorApp: ErrorApp?) {
        errorApp?.let {
            val errorAppUI = errorAppUIFactory.build(errorApp, ::getManga)
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

    private fun setupTab() {
        binding.apply {
            mangaDetailAssessmentView.hide()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            buttonAssess.hide()
                            mangaDetailInfoView.visible()
                            mangaDetailAssessmentView.hide()
                        }
                        1 -> {
                            buttonAssess.visible()
                            mangaDetailInfoView.hide()
                            mangaDetailAssessmentView.visible()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

    }
}