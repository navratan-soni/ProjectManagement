package com.pmgmt.mainapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pmgmt.mainapp.data.model.ArchitectLoginResponse

class LoginViewModel(private val repository: ArchitectRepository) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = repository.loginArchitect()
                _loginState.value = LoginState.Success(response.projects)
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Login failed")
            }
        }
    }

    sealed class LoginState {
        object Loading : LoginState()
        data class Success(val projects: List<Project>) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
