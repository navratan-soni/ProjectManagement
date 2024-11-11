package com.pmgmt.mainapp.data.model

data class Project(
    val projectId: String,
    val projectName: String,
    val userRole: String,
    val location: String,
    val startDate: String,
    val endDate: String
)