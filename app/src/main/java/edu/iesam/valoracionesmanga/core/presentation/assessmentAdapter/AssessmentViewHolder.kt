package edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.valoracionesmanga.core.extensions.loadUrl
import edu.iesam.valoracionesmanga.databinding.ViewAssessmentItemBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga

class AssessmentViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewAssessmentItemBinding

    fun bind(model: AssessmentManga, onClick: ((String) -> Unit)?) {
        binding = ViewAssessmentItemBinding.bind(view)
        binding.apply {
            user.text = model.assessment.user
            score.text =  model.assessment.score.toString()
            comment.text = model.assessment.comment

            model.manga?.let { manga ->
                mangaImage.loadUrl(manga.img)
            }

            if (onClick != null) {
                user.setOnClickListener {
                    onClick(model.assessment.user)
                }
            }

        }
    }

}