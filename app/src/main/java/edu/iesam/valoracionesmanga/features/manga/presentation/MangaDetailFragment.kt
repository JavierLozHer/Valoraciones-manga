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
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.core.presentation.visible
import edu.iesam.valoracionesmanga.databinding.FragmentMangaDetailBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaDetailFragment: Fragment() {

    private var _binding: FragmentMangaDetailBinding? = null
    private val binding get() = _binding!!

    private val mangaArgs: MangaDetailFragmentArgs by navArgs()

    private val viewModel : MangaDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = binding.profileToolbar.toolbar

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        setupObserver()
        setupTab()
        viewModel.getManga(mangaArgs.mangaId)


    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            shoLoading(uiState.isLoading)
            bindData(uiState.manga, uiState.score)
        }
    }

    private fun shoLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visible()
        } else {
            binding.progressBar.hide()
        }
    }

    private fun bindData(manga: Manga?, score: Double?) {
        manga?.let {
            binding.profileToolbar.toolbar.title = manga.title
            binding.mangaDetailInfoView.render(manga, score)
            binding.mangaDetailAssessmentView.render()
        }
    }

    private fun setupTab() {
        binding.apply {
            mangaDetailAssessmentView.hide()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            mangaDetailInfoView.visible()
                            mangaDetailAssessmentView.hide()
                        }
                        1 -> {
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