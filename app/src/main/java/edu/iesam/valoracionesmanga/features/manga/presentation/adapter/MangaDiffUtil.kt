package edu.iesam.valoracionesmanga.features.manga.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.manga.domain.Manga

class MangaDiffUtil: DiffUtil.ItemCallback<Manga>() {
    override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem == newItem
    }
}