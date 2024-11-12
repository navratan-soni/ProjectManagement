package com.pmgmt.mainapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pmgmt.mainapp.presentation.ui.screen.LoginScreen
import com.pmgmt.mainapp.presentation.ui.screen.ProjectDetailsScreen
import com.pmgmt.mainapp.presentation.ui.screen.ProjectSelectionScreen
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel

@Composable
fun AppNavHost(navController: NavHostController,
               loginViewModel: LoginViewModel,
               projectDetailsViewModel: ProjectDetailsViewModel
               ) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = loginViewModel) { projects ->
                navController.navigate("projectSelection") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
        composable("projectSelection") {
            val projects = (loginViewModel.loginState.value as? LoginViewModel.LoginState.Success)?.projects ?: emptyList()
            ProjectSelectionScreen(
                projects = projects,
                onProjectSelected = { projectId ->
                   navController.navigate("projectDetails/$projectId")
                }
            )
        }
        composable("projectDetails/{projectId}") { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId") ?: ""
            ProjectDetailsScreen(
                projectId = projectId,
                projectDetailsViewModel = projectDetailsViewModel,
                onFeedClick = { navController.navigate("liveFeed") }
            )
        }
        composable("liveFeed") {
            // Placeholder for future LiveFeedScreen content
        }
    }
}
