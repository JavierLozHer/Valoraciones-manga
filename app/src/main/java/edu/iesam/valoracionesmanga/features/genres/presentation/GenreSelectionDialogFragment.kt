package edu.iesam.valoracionesmanga.features.genres.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.DialogFragmentGenresSelectionBinding
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionViewModel.Genre
import edu.iesam.valoracionesmanga.features.genres.presentation.adapter.GenreAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreSelectionDialogFragment : DialogFragment() {

    interface GenreSelectionListener {
        fun onGenresSelected(selectedGenres: List<String>)
    }

    private var _binding: DialogFragmentGenresSelectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenreSelectionViewModel by viewModel()

    private val genreAdapter = GenreAdapter()

    private lateinit var skeleton : Skeleton

    private var listener: GenreSelectionListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = requireParentFragment() as GenreSelectionListener
    }

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
            listener?.onGenresSelected(list)
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


}
