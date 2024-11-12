package com.pmgmt.mainapp.data.network

import com.pmgmt.mainapp.data.model.ArchitectLoginResponse
import com.pmgmt.mainapp.data.model.Project
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("architect_login.json")
    suspend fun loginArchitect(): ArchitectLoginResponse

    @GET("project_details.json")
    suspend fun getProjectDetails(@Query("projectId") projectId: String): Project
}