package com.pmgmt.mainapp.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.pmgmt.mainapp.data.model.Project
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

/*
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
fun DisplayProjectDetails(
    project: Project,
    onFeedClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar with title and feed icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = project.projectName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            IconButton(onClick = onFeedClick) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Live Feed",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Project image
        AsyncImage(
            model = project.imageUrl,
            contentDescription = "Project Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Project details
        Text(text = "Location: ${project.location}", fontSize = 18.sp)
        Text(text = "Start Date: ${project.startDate}", fontSize = 18.sp)
        Text(text = "End Date: ${project.endDate}", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = project.description ?: "No description available.", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(32.dp))

        // Four static buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StaticButton(text = "Highlights")
            StaticButton(text = "Milestones")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StaticButton(text = "Inventory")
            StaticButton(text = "Purchases")
        }
    }
}

@Composable
fun StaticButton(text: String) {
    Button(
        onClick = { */
/* TODO: Implement onClick *//*
 },
        modifier = Modifier
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}*/
