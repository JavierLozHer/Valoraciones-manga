package edu.iesam.valoracionesmanga.features.manga.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import edu.iesam.valoracionesmanga.core.extensions.loadUrl
import edu.iesam.valoracionesmanga.databinding.ViewMangaDetailInfoBinding
import edu.iesam.valoracionesmanga.features.manga.domain.Manga
import edu.iesam.valoracionesmanga.features.manga.presentation.staffAdapter.StaffAdapter
import java.util.Locale

class MangaDetailInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewMangaDetailInfoBinding.inflate(LayoutInflater.from(context), this, true)

    private val staffAdapter = StaffAdapter()

    fun render(manga: Manga, mangaScore: Double?) {
        binding.apply {
            title.text = manga.title
            image.loadUrl(manga.img)
            synopsis.text = manga.synopsis
            genresChip.removeAllViews()
            manga.genres.forEach { gender ->
                val chip = Chip(context)
                chip.text = gender
                chip.isCheckable = false
                chip.isClickable = false
                genresChip.addView(chip)
            }

            mangaScore?.let {
                score.text = String.format(Locale.US, "%.2f", it)
            }

            startYear.text = manga.startDate
            finishYear.text = manga.finishDate

            staffRecyclerView.layoutManager = GridLayoutManager(context, 3)
            staffRecyclerView.adapter = staffAdapter
            staffAdapter.submitList(manga.staff)
        }
    }

}