package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

class AssessmentDiffUtil: DiffUtil.ItemCallback<Assessment>() {

    override fun areContentsTheSame(oldItem: Assessment, newItem: Assessment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Assessment, newItem: Assessment): Boolean {
        return oldItem == newItem
    }
}