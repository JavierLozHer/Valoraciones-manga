package edu.iesam.valoracionesmanga.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            profileToolbar.toolbar.title = getString(R.string.profile)
            buttonLogin.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileToUserForm(true))
            }
            buttonCreateUser.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileToUserForm(false))
            }
        }
    }
}