package edu.iesam.valoracionesmanga.features.assessment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentAssessmentBinding

class AssessmentFragment : Fragment() {
    private var _binding: FragmentAssessmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssessmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.profileToolbar.toolbar.title = getString(R.string.assessment)
    }
}