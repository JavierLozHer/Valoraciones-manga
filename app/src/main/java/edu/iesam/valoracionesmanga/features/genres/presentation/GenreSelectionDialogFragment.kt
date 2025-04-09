package edu.iesam.valoracionesmanga.features.genres.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.databinding.DialogFragmentGenresSelectionBinding
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionViewModel.Genre
import edu.iesam.valoracionesmanga.features.genres.presentation.adapter.GenreAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreSelectionDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentGenresSelectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenreSelectionViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    private val genreAdapter = GenreAdapter()

    private lateinit var skeleton : Skeleton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentGenresSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = genreAdapter
        }
        setupObserver()
        setUpRecyclerView()

        binding.btnAccept.setOnClickListener {
            val list = genreAdapter.currentList.filter { it.isSelected }.map { it.name }
            requireActivity().supportFragmentManager.setFragmentResult(
                "requestKey",
                bundleOf("selectedGenres" to list.toTypedArray())
            )
            dismiss()
        }

        binding.btnCancel.setOnClickListener { dismiss() }
        viewModel.getGenres()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerView.adapter = genreAdapter
            skeleton = recyclerView.applySkeleton(R.layout.view_genre_selection)
        }

    }

    private fun setupObserver() {
        viewModel.uiState.observe(this) { uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.genres)
            showError(uiState.errorApp)
        }
    }

    private fun bindData(genres: List<Genre>?) {
        genres?.let {
            genreAdapter.submitList(it)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun showError(errorApp: ErrorApp?) {
        errorApp?.let {
            val errorAppUI = errorAppUIFactory.build(errorApp, viewModel::getGenres)
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }


}
