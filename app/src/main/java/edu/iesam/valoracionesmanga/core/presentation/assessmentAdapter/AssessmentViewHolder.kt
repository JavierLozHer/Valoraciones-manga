package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.valoracionesmanga.databinding.ViewAssessmentItemBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment

class AssessmentViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewAssessmentItemBinding

    fun bind(model: Assessment) {
        binding = ViewAssessmentItemBinding.bind(view)

        binding.apply {
            user.text = model.user
            score.text =  model.score.toString()
            comment.text = model.comment
        }
    }

}