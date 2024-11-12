package com.pmgmt.mainapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

class ProjectDetailsViewModelFactory(
    private val repository: ArchitectRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}