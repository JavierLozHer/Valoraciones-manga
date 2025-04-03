package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

class AssessmentAdapter: ListAdapter<Assessment, AssessmentViewHolder>(AssessmentDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssessmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_assessment_item, parent, false)
        return AssessmentViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: AssessmentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}