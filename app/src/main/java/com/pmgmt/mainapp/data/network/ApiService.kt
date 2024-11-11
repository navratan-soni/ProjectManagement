package com.pmgmt.mainapp.data.network

import com.pmgmt.mainapp.data.model.ArchitectLoginResponse
import retrofit2.http.GET

interface ApiService {
    @GET("architect_login.json")
    suspend fun loginArchitect(): ArchitectLoginResponse
}