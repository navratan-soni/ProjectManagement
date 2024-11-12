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

class LoginViewModel(private val repository: ArchitectRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    // SharedFlow to emit a one-time navigation event
    private val _navigateToProjects = MutableSharedFlow<List<Project>>(replay = 0)
    val navigateToProjects: SharedFlow<List<Project>> = _navigateToProjects

    private val _selectedProject = MutableStateFlow<Project?>(null)
    val selectedProject: StateFlow<Project?> = _selectedProject

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = repository.loginArchitect()
                if (response.success) {
                    _loginState.value = LoginState.Success(response.projects)
                    _navigateToProjects.emit(response.projects) // Emit a one-time navigation event
                } else {
                    _loginState.value = LoginState.Error("Login failed")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("An error occurred")
            }
        }
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
