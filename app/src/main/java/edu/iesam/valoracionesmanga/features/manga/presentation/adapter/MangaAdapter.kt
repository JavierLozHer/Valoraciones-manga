package edu.iesam.valoracionesmanga.features.manga.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.features.manga.domain.Manga

class MangaAdapter: ListAdapter<Manga, MangaViewHolder>(MangaDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_manga_item, parent, false)
        return MangaViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}