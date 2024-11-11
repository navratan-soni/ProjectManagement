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
            val loginState by viewModel.loginState.collectAsState()
            if (loginState is LoginViewModel.LoginState.Success) {
                val projects = (loginState as LoginViewModel.LoginState.Success).projects
                ProjectSelectionScreen(
                    projects = projects,
                    onProjectSelected = { project ->
                        viewModel.selectProject(project)
                        // You can navigate to another screen from here if needed
                    }
                )
            }
        }
    }
}