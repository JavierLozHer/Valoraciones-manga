package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentMangaUseCase

class AssessmentDiffUtil: DiffUtil.ItemCallback<AssessmentManga>() {

    override fun areContentsTheSame(oldItem: AssessmentManga, newItem: AssessmentManga): Boolean {
        return oldItem.assessment.id == newItem.assessment.id
    }

    override fun areItemsTheSame(oldItem: AssessmentManga, newItem: AssessmentManga): Boolean {
        return oldItem == newItem
    }
}