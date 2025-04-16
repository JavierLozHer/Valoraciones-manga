package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.extensions.loadUrl
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.core.presentation.showSnackbar
import edu.iesam.valoracionesmanga.core.presentation.visible
import edu.iesam.valoracionesmanga.databinding.FragmentAssesssmentFormBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.Assessment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssessmentFormFragment: Fragment() {
    private var _binding: FragmentAssesssmentFormBinding? = null
    private val binding get() = _binding!!

    private val assessmentArgs: AssessmentFormFragmentArgs by navArgs()

    private val viewModel: AssessmentFormViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssesssmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
        setupObserver()
        getAssessment()
        setupListener()
    }

    private fun getAssessment() {
        viewModel.getAssessment(assessmentArgs.mangaId)
    }

    private fun setup() {
        binding.apply {
            val toolbar = assessmentFormToolbar.toolbar

            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

            toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            assessmentFormToolbar.toolbar.title = getString(R.string.assessment)
            mangaImage.loadUrl(assessmentArgs.mangaImage)
            numberPicker.minValue = 1
            numberPicker.maxValue = 10
        }
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            showLoading(uiState.isLoading)
            saved(uiState.isSaved)
            bindData(uiState.assessment)
            showError(uiState.errorApp)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visible()
        } else {
            binding.progressBar.hide()
        }
    }


    private fun saved(saved: Boolean) {
        if (saved) {
            view?.showSnackbar(getString(R.string.assessment_add))
            findNavController().navigateUp()
        }
    }

    private fun bindData(assessment: Assessment?) {
        assessment?.let {
            binding.apply {
                numberPicker.value = assessment.score.toInt()
                comment.setText(assessment.comment)
            }
        }
    }

    private fun showError(errorApp: ErrorApp?) {
        errorApp?.let {
            val errorAppUI = errorAppUIFactory.build(errorApp)
            binding.errorAppView.render(errorAppUI)
            binding.assessmentForm.hide()
        } ?: run {
            binding.assessmentForm.visible()
            binding.errorAppView.hide()
        }
    }

    private fun setupListener() {
        binding.apply {
            buttonAssess.setOnClickListener {
                val score = numberPicker.value
                val comment = comment.text.toString()
                viewModel.save(score, comment, assessmentArgs.mangaId)
            }
        }
    }

}