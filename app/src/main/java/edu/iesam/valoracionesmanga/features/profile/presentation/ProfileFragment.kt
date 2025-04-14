package edu.iesam.valoracionesmanga.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.assessmentAdapter.AssessmentAdapter
import edu.iesam.valoracionesmanga.core.presentation.errorView.ErrorAppUIFactory
import edu.iesam.valoracionesmanga.core.presentation.hide
import edu.iesam.valoracionesmanga.databinding.FragmentProfileBinding
import edu.iesam.valoracionesmanga.features.assessment.domain.AssessmentManga
import edu.iesam.valoracionesmanga.features.profile.domain.User
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val assessmentAdapter = AssessmentAdapter(null)

    private val viewModel : ProfileViewModel by viewModel()
    private val errorAppUIFactory: ErrorAppUIFactory by inject()

    private lateinit var skeleton : Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.profileToolbar.toolbar.title = getString(R.string.profile)
        setupRecyclerView()
        setUpObserver()

        binding.buttonLogout.setOnClickListener{
            viewModel.logout()
            assessmentAdapter.submitList(listOf())
        }

        viewModel.getUserLogged()
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

    private fun setUpObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            showLoading(uiState.isLoading)
            bindData(uiState.assessment, uiState.user)
            showError(uiState.errorApp)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun bindData(assessments: List<AssessmentManga>?, user: User?) {
        binding.apply {
            assessments?.let {
                assessmentAdapter.submitList(assessments)
                buttonsLogin.visibility = View.GONE
                buttonLogout.visibility = View.VISIBLE
            } ?: run {

                user?.let {
                    viewModel.getAssessment(user.email)
                    buttonsLogin.visibility = View.GONE

                } ?: run {
                    buttonsLogin.visibility = View.VISIBLE
                    buttonLogout.visibility = View.GONE
                    buttonLogin.setOnClickListener {
                        findNavController().navigate(
                            ProfileFragmentDirections.actionProfileToUserForm(
                                true
                            )
                        )
                    }
                    buttonCreateUser.setOnClickListener {
                        findNavController().navigate(
                            ProfileFragmentDirections.actionProfileToUserForm(
                                false
                            )
                        )
                    }
                }

            }
        }
    }

    private fun showError(errorApp: ErrorApp?) {
        errorApp?.let {
            viewModel.getUserLogged()
            val errorAppUI = errorAppUIFactory.build(errorApp, viewModel::getUserLogged)
            binding.errorAppView.render(errorAppUI)
        } ?: run {
            binding.errorAppView.hide()
        }
    }

}