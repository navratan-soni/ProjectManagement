package com.pmgmt.mainapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: ArchitectRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = repository.loginArchitect()
                _loginState.value = if (response.success) {
                    LoginState.Success(response.projects)
                } else {
                    LoginState.Error("Login failed")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred")
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }

    fun selectProject(project: Project) {
        _selectedProject.value = project
        // Persist the selected project ID
        // This could be stored in a DataStore or SharedPreferences
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val projects: List<Project>) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}