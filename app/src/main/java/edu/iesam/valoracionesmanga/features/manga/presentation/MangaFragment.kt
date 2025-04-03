package edu.iesam.valoracionesmanga.features.manga.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentMangaBinding
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionDialogFragment
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import edu.iesam.valoracionesmanga.features.manga.presentation.adapter.MangaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaFragment : Fragment(), GenreSelectionDialogFragment.GenreSelectionListener {

    private var _binding: FragmentMangaBinding? = null
    private val binding get() = _binding!!

    private val mangaAdapter = MangaAdapter(::onClick)

    private val viewModel: MangaViewModel by viewModel()

    private lateinit var skeleton : Skeleton

    fun onClick(id: String) {
        findNavController().navigate(MangaFragmentDirections.actionMangaToMangaDetail(id))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaBinding.inflate(inflater, container, false)

        binding.genreButton.setOnClickListener {
            GenreSelectionDialogFragment().show(childFragmentManager, "GenreSelectionDialog")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.profileToolbar.toolbar.title = getString(R.string.manga)

        setUpRecyclerView()
        setupObserver()
        viewModel.getMangas()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            mangaRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            mangaRecyclerView.adapter = mangaAdapter
            skeleton = mangaRecyclerView.applySkeleton(R.layout.view_manga_item)
        }

    }

    private fun setupObserver() {
        val observer = Observer<MangaViewModel.UiState>{ uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.mangas)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(mangas: List<Manga>?) {
        mangas?.let {
            Log.d("@dev", "Mangas $it")
            mangaAdapter.submitList(mangas)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    override fun onGenresSelected(selectedGenres: List<String>) {
        Log.d("@dev", "Generos $selectedGenres")
        viewModel.getMangasByGenres(selectedGenres)
    }
}