package com.pmgmt.mainapp.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

@Composable
fun ProjectDetailsScreen(
    projectId: String,
    projectDetailsViewModel: ProjectDetailsViewModel,
    onFeedClick: () -> Unit
) {
    // Trigger data load when the screen is displayed
    LaunchedEffect(projectId) {
        projectDetailsViewModel.loadProjectDetails(projectId)
    }

    // Observe project details state from the ViewModel
    val project by projectDetailsViewModel.projectDetails.collectAsState()

    // Display the UI only when project details are available
    project?.let {
        DisplayProjectDetails(it, onFeedClick)
    } ?: run {
        // Placeholder content while loading
        Text(
            text = "Loading project details...",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DisplayProjectDetails(project: Project, onFeedClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = project.imageUrl,
            contentDescription = "Project Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = project.projectName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Location: ${project.location}")
        Text(text = "Start Date: ${project.startDate}")
        Text(text = "End Date: ${project.endDate}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = project.description ?: "No description available.")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onFeedClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Live Feed")
        }
    }
}