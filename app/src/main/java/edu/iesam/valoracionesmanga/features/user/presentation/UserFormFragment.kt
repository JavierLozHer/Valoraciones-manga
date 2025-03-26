package edu.iesam.valoracionesmanga.features.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import edu.iesam.valoracionesmanga.R
import edu.iesam.valoracionesmanga.core.domain.ErrorApp
import edu.iesam.valoracionesmanga.core.presentation.showSnackbar
import edu.iesam.valoracionesmanga.core.presentation.validateEmail
import edu.iesam.valoracionesmanga.core.presentation.validatePassword
import edu.iesam.valoracionesmanga.databinding.FragmentUserFormBinding
import edu.iesam.valoracionesmanga.features.user.domain.LoginUseCase
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
        setupObserver()
    }

    private fun bindLogin() {
        binding.apply {
            userFormToolbar.toolbar.title = getString(R.string.login)
            actionButton.text = getString(R.string.login)
            actionButton.setOnClickListener {
                val email = userName.text.toString()
                val password = userPasswd.text.toString()

                if (!validateEmail(email)) {
                    view?.showSnackbar(getString(R.string.type_email))
                } else if(!validatePassword(password)) {
                    view?.showSnackbar(getString(R.string.password_length))
                } else {
                    viewModel.login(email, password)
                }
            }
        }

    }

    private fun bindCreateUser() {
        binding.apply {
            userFormToolbar.toolbar.title = getString(R.string.create_user)
            actionButton.text = getString(R.string.create_user)
            actionButton.setOnClickListener {
                val email = userName.text.toString()
                val password = userPasswd.text.toString()

                if (!validateEmail(email)) {
                    view?.showSnackbar(getString(R.string.type_email))
                } else if(!validatePassword(password)) {
                    view?.showSnackbar(getString(R.string.password_length))
                } else {
                    viewModel.createUser(email, password)
                }
            }
        }
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            handleLogged(uiState.isLogged)
            handleErrors(uiState.error)
        }
    }

    private fun handleLogged(logged: Boolean) {
        if (logged) {
            findNavController().navigate(
                UserFormFragmentDirections.actionUserFormToProfile(),
                navOptions {
                    popUpTo(R.id.fragment_user_form) { inclusive = true }
                }
            )
        }
    }

    private fun handleErrors(error: ErrorApp?) {
        error?.let {
            if (it == ErrorApp.DataErrorApp) {
                if (userArgs.isLogin) {
                    view?.showSnackbar(getString(R.string.user_incorrect))
                } else {
                    view?.showSnackbar(getString(R.string.user_exists))
                }

            }
        }
    }

}