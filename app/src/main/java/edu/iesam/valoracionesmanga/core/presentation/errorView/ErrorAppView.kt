package edu.iesam.valoracionesmanga.core.presentation.errorView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.core.presentation.visible
import edu.iesam.valoracionesmanga.databinding.ViewErrorBinding

class ErrorAppView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        hide()
    }

    fun render(errorAppUI: ErrorAppUI)   {
        binding.apply {
            titleError.text = errorAppUI.getTitleError()
            descriptionError.text = errorAppUI.getDescriptionError()

            val actionRetry = errorAppUI.getActionRetry()

            if (actionRetry != null) {
                buttonError.visibility = VISIBLE
                buttonError.setOnClickListener{
                    actionRetry()
                }
            } else {
                buttonError.visibility = GONE
            }
        }
        visible()
    }

}