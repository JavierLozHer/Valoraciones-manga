package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter.AssessmentAdapter
import edu.iesam.valoracionesmanga.databinding.ViewAssessmentMangaBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga

class AssessmentMangaView@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewAssessmentMangaBinding.inflate(LayoutInflater.from(context), this, true)

    private val assessmentAdapter = AssessmentAdapter()

    fun render(assessments: List<AssessmentManga>) {
        Log.d("@dev", assessments.toString())
        binding.apply {
            assessmentRecyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            assessmentRecyclerView.adapter = assessmentAdapter
        }
        assessmentAdapter.submitList(assessments)
    }
}