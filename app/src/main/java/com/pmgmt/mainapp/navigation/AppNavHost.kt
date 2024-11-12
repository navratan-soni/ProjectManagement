package com.pmgmt.mainapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pmgmt.mainapp.presentation.ui.screen.LoginScreen
import com.pmgmt.mainapp.presentation.ui.screen.ProjectSelectionScreen
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel

@Composable
fun AppNavHost(navController: NavHostController, viewModel: LoginViewModel) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = viewModel) { projects ->
                navController.navigate("projectSelection") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
        composable("projectSelection") {
            val projects = (viewModel.loginState.value as? LoginViewModel.LoginState.Success)?.projects ?: emptyList()
            ProjectSelectionScreen(
                projects = projects,
                onProjectSelected = { project ->
                    viewModel.selectProject(project)
                }
            )
        }
    }
}
