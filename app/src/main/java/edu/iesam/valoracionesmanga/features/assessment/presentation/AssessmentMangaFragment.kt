package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edu.iesam.valoracionesmanga.databinding.ViewAssessmentMangaBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

class AssessmentMangaFragment@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewAssessmentMangaBinding.inflate(LayoutInflater.from(context), this, true)

    fun render(assessments: List<Assessment>) {
        binding.title.text = "Lista"
    }
}