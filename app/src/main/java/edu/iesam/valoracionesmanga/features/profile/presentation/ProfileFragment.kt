package edu.iesam.valoracionesmanga.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProfileViewModel by viewModel()

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
        setUpObserver()
        viewModel.getUserLogged()
    }

    private fun setUpObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.apply {
                uiState.user?.let {
                    user.visibility = View.VISIBLE
                    buttonsLogin.visibility = View.GONE
                    userName.text = it.email
                }?: run {
                    buttonsLogin.visibility = View.VISIBLE
                    user.visibility = View.GONE
                    buttonLogin.setOnClickListener {
                        findNavController().navigate(ProfileFragmentDirections.actionProfileToUserForm(true))
                    }
                    buttonCreateUser.setOnClickListener {
                        findNavController().navigate(ProfileFragmentDirections.actionProfileToUserForm(false))
                    }
                }
            }
        }
    }




}