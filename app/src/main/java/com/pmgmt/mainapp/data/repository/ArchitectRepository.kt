package com.pmgmt.mainapp.data.repository

import android.content.Context
import com.pmgmt.mainapp.data.model.ArchitectLoginResponse
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.data.network.ApiService

class ArchitectRepository(private val apiService: ApiService) {
    suspend fun loginArchitect(): ArchitectLoginResponse = apiService.loginArchitect()
    suspend fun getProjectDetails(projectId: String): Project = apiService.getProjectDetails(projectId)
}