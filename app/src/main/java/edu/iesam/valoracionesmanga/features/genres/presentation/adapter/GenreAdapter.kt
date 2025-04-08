package edu.iesam.valoracionesmanga.features.genres.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.features.genres.presentation.GenreSelectionViewModel

class GenreAdapter: ListAdapter<GenreSelectionViewModel.Genre, GenreViewHolder>(GenreDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_genre_selection, parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}