package com.pmgmt.mainapp.data.model

data class ArchitectLoginResponse(
    val success: Boolean,
    val userId: String,
    val name: String,
    val role: String,
    val token: String,
    val projects: List<Project>
)