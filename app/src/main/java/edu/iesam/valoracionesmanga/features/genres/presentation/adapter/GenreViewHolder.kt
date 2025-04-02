package edu.iesam.valoracionesmanga.features.genres.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.valoracionesmanga.databinding.ViewGenreSelectionBinding
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionViewModel

class GenreViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewGenreSelectionBinding

    fun bind(genre: GenreSelectionViewModel.Genre) {
        binding = ViewGenreSelectionBinding.bind(view)
        binding.genre.text = genre.name
        binding.genreCheckbox.isChecked = genre.isSelected

        binding.root.setOnClickListener {
            val newSelectionState = !binding.genreCheckbox.isChecked
            binding.genreCheckbox.isChecked = newSelectionState
        }

        binding.root.setOnClickListener {
            val newSelectionState = !binding.genreCheckbox.isChecked
            binding.genreCheckbox.isChecked = newSelectionState
            genre.isSelected = newSelectionState
        }
    }

}