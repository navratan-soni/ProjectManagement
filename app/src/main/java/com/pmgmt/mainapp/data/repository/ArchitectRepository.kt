package com.pmgmt.mainapp.data.repository

import com.pmgmt.mainapp.data.model.ArchitectLoginResponse
import com.pmgmt.mainapp.data.network.ApiService

class ArchitectRepository(private val apiService: ApiService) {
    suspend fun loginArchitect(): ArchitectLoginResponse {
        return apiService.loginArchitect()
    }
}