package com.pmgmt.mainapp.data.model
import com.pmgmt.mainapp.data.model.Project

data class ProjectListResponse(
    val status: String,
    val projects: List<Project>
)
