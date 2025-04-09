package edu.iesam.valoracionesmanga.features.manga.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentSearchBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import edu.iesam.valoracionesmanga.features.manga.presentation.adapter.MangaAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mangaAdapter = MangaAdapter(::onClick)

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var skeleton : Skeleton

    fun onClick(id: String, title: String) {
        findNavController().navigate(SearchFragmentDirections.actionMangaSearchToMangaDetail(id, title))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchToolbar.toolbar.title = getString(R.string.search)
        
        setSearchMenu()
        setUpRecyclerView()
        setupObserver()
        viewModel.getMangas()
    }


    private fun setSearchMenu() {
        binding.searchToolbar.toolbar.inflateMenu(R.menu.menu_search)

        val searchItem = binding.searchToolbar.toolbar.menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchMangas(newText)
                return false
            }
        })
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
        val observer = Observer<SearchViewModel.UiState>{ uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.mangas)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(mangas: List<Manga>?) {
        mangas?.let {
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
}