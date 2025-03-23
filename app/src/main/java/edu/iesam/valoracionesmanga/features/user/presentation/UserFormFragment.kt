package edu.iesam.valoracionesmanga.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.databinding.FragmentProfileBinding
import edu.iesam.valoracionesmanga.databinding.FragmentUserFormBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFormFragment: Fragment() {

    private var _binding: FragmentUserFormBinding? = null
    private val binding get() = _binding!!

    private val userArgs: UserFormFragmentArgs by navArgs()

    private val viewModel : UserFormViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (userArgs.isLogin) {
            bindLogin()
        } else {
            bindCreateUser()
        }
    }

    private fun bindLogin() {
        binding.apply {
            userFormToolbar.toolbar.title = getString(R.string.login)
            actionButton.text = getString(R.string.login)
            actionButton.setOnClickListener {
                val username = userName.text.toString()
                val password = userPasswd.text.toString()

                viewModel.login(username, password)
            }
        }

    }

    private fun bindCreateUser() {
        binding.apply {
            userFormToolbar.toolbar.title = getString(R.string.create_user)
            actionButton.text = getString(R.string.create_user)
            actionButton.setOnClickListener {
                val username = userName.text.toString()
                val password = userPasswd.text.toString()

                viewModel.createUser(username, password)
            }
        }
    }

}