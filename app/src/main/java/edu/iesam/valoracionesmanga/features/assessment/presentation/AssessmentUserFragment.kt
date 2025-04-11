package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter.AssessmentAdapter
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.databinding.FragmentAssessmentUserBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssessmentUserFragment: Fragment() {
    private var _binding: FragmentAssessmentUserBinding? = null
    private val binding get() = _binding!!

    private val assessmentArgs: AssessmentUserFragmentArgs by navArgs()

    private val assessmentAdapter = AssessmentAdapter(null)

    private val viewModel: AssessmentUserViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    private lateinit var skeleton : Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssessmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        setupRecyclerView()
        setupObserver()
        getAssessmentUser()
    }

    private fun setupToolBar() {
        val toolbar = binding.assessmentToolbar.toolbar
        toolbar.title = assessmentArgs.email

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }



    private fun setupRecyclerView() {
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
        val observer = Observer<AssessmentUserViewModel.UiState> {uiState ->
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
            val errorAppUI = errorAppUIFactory.build(errorApp, ::getAssessmentUser)
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

    private fun getAssessmentUser() {
        viewModel.getAssessment(assessmentArgs.email)
    }
}