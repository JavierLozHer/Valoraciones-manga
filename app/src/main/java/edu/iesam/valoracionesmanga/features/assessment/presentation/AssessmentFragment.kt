package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter.AssessmentAdapter
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.databinding.FragmentAssessmentBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import edu.iesam.valoracionesmanga.features.assessment.domain.GetAssessmentMangaUseCase
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssessmentFragment : Fragment() {
    private var _binding: FragmentAssessmentBinding? = null
    private val binding get() = _binding!!

    private val assessmentAdapter = AssessmentAdapter()

    private val viewModel: AssessmentViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    private lateinit var skeleton : Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssessmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.assessmentToolbar.toolbar.title = getString(R.string.assessment)
        setUpRecyclerView()
        setupObserver()
        viewModel.getAssessment()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            assessmentRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            assessmentRecyclerView.adapter = assessmentAdapter
            skeleton = assessmentRecyclerView.applySkeleton(R.layout.view_assessment_item)
        }

    }

    private fun setupObserver() {
        val observer = Observer<AssessmentViewModel.UiState> {uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.assessment)
            showError(uiState.errorApp)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun bindData(assessment: List<AssessmentManga>?) {
        assessment?.let {
            assessmentAdapter.submitList(assessment)
        }
    }

    private fun showError(errorApp: ErrorApp?) {
        errorApp?.let {
            val errorAppUI = errorAppUIFactory.build(errorApp, viewModel::getAssessment)
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

}