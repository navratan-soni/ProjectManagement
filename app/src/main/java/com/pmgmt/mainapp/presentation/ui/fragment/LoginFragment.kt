package com.pmgmt.mainapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel

import androidx.lifecycle.ViewModelProvider
import com.pmgmt.mainapp.MainActivity
import com.pmgmt.mainapp.data.network.ApiService
import com.pmgmt.mainapp.data.network.NetworkModule
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.databinding.FragmentLoginBinding
import com.pmgmt.mainapp.presentation.viewmodel.factory.LoginViewModelFactory

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var apiService: ApiService
    private lateinit var architectRepository: ArchitectRepository

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory(architectRepository))
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = NetworkModule.provideApiService(requireContext())
        architectRepository = ArchitectRepository(apiService)

        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            loginViewModel.login()
        }
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                //is LoginViewModel.LoginState.Loading -> showLoading()
                is LoginViewModel.LoginState.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
                //is LoginViewModel.LoginState.Error -> showError(state.message)
                else -> Unit
            }
        }
    }
}
