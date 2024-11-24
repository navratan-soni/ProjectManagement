package com.pmgmt.mainapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import kotlinx.coroutines.launch

class ProjectListViewModel(private val repository: ArchitectRepository) : ViewModel() {
    private val _projectListState = MutableLiveData<ProjectListState>()
    val projectListState: LiveData<ProjectListState> = _projectListState

    fun loadProjects(role: String, token: String) {
        viewModelScope.launch {
            _projectListState.value = ProjectListState.Loading
            try {
                val projects = repository.getProjects(role)
                _projectListState.value = ProjectListState.Success(projects)
            } catch (e: Exception) {
                _projectListState.value = ProjectListState.Error("Failed to load projects")
            }
        }
    }

    sealed class ProjectListState {
        object Loading : ProjectListState()
        data class Success(val projects: List<Project>) : ProjectListState()
        data class Error(val message: String) : ProjectListState()
    }
}
