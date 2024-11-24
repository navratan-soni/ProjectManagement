package com.pmgmt.mainapp.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

@Composable
fun ProjectSelectionScreen(
    projects: List<Project>,
    onProjectSelected: (String) -> Unit
) {

    /*Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Select a Project",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(items = projects) { project ->
                ProjectItem(project = project,
                    onProjectSelected = onProjectSelected)
            }
        }
    }
}

@Composable
fun ProjectItem(project: Project, onProjectSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProjectSelected(project.projectId) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.projectName, style = MaterialTheme.typography.titleLarge)
            Text(text = "Role: ${project.userRole}")
            Text(text = "Location: ${project.location}")
            Text(text = "Duration: ${project.startDate} - ${project.endDate}")
        }
    }*/
}

