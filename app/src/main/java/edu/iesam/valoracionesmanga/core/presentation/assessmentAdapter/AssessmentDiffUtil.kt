package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentMangaUseCase

class AssessmentDiffUtil: DiffUtil.ItemCallback<GetAssessmentMangaUseCase.AssessmentManga>() {

    override fun areContentsTheSame(oldItem: GetAssessmentMangaUseCase.AssessmentManga, newItem: GetAssessmentMangaUseCase.AssessmentManga): Boolean {
        return oldItem.assessment.id == newItem.assessment.id
    }

    override fun areItemsTheSame(oldItem: GetAssessmentMangaUseCase.AssessmentManga, newItem: GetAssessmentMangaUseCase.AssessmentManga): Boolean {
        return oldItem == newItem
    }
}