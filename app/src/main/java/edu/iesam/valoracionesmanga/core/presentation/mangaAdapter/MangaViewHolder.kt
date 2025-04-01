package edu.iesam.valoracionesmanga.core.presentation.mangaAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import edu.iesam.valoracionesmanga.core.extensions.loadUrl
import edu.iesam.valoracionesmanga.databinding.ViewMangaItemBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Manga

class MangaViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewMangaItemBinding

    fun bind(model: Manga) {
        binding = ViewMangaItemBinding.bind(view)

        binding.apply {
            image.loadUrl(model.img)
            title.text = model.title
            author.text = model.staff[0].name

            genresChip.removeAllViews()

            model.genres.forEach { gender ->
                val chip = Chip(itemView.context)
                chip.text = gender
                chip.isCheckable = false
                chip.isClickable = false


                genresChip.addView(chip)
            }
        }

    }
}