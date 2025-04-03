package edu.iesam.valoracionesmanga.features.manga.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edu.iesam.valoracionesmanga.databinding.ViewMangaDetailInfoBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Manga

class MangaDetailInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewMangaDetailInfoBinding.inflate(LayoutInflater.from(context), this, true)

    fun render(manga: Manga) {
        binding.title.text = manga.title
    }

}