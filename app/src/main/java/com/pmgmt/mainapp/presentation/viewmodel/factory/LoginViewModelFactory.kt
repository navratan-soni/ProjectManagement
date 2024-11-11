package com.pmgmt.mainapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val repository: ArchitectRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}