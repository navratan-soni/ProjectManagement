package com.pmgmt.mainapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectDetailsViewModel(private val architectRepository: ArchitectRepository
) : ViewModel() {

    private val _projectDetails = MutableStateFlow<Project?>(null)
    val projectDetails: StateFlow<Project?> = _projectDetails

    fun loadProjectDetails(projectId: String) {
        viewModelScope.launch {
            val project = architectRepository.getProjectDetails(projectId)
            _projectDetails.value = project
        }
    }
}