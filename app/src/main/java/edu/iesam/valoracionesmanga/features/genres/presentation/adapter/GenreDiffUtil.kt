package edu.iesam.valoracionesmanga.features.genres.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionViewModel.Genre

class GenreDiffUtil: DiffUtil.ItemCallback<Genre>() {

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}