package com.pmgmt.mainapp.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.data.model.Project

/*
@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: (List<Project>) -> Unit) {
    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is LoginViewModel.LoginState.Loading -> Text("Loading...")
        is LoginViewModel.LoginState.Success -> {
            val projects = (loginState as LoginViewModel.LoginState.Success).projects
            onLoginSuccess(projects)
        }
        is LoginViewModel.LoginState.Error -> Text("Error: ${(loginState as LoginViewModel.LoginState.Error).message}")
        else -> Button(onClick = { viewModel.login() }) {
            Text("Login as Architect")
        }
    }
}*/

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (List<Project>) -> Unit
) {
    // Collect login state from the ViewModel
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    // Handle different login states
    when (loginState) {
        /*is LoginViewModel.LoginState.Success -> {
            // Call onLoginSuccess with the list of projects if login is successful
            val projects = (loginState as LoginViewModel.LoginState.Success).projects
            onLoginSuccess(projects)
        }*/
        is LoginViewModel.LoginState.Success -> {
            val projects = (loginState as LoginViewModel.LoginState.Success).projects
            onLoginSuccess(projects)
                //viewModel.resetLoginState() // Reset the state after navigating
        }
        is LoginViewModel.LoginState.Loading -> {
            // Show a loading indicator or text message for loading state
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Logging in...")
            }
        }
        is LoginViewModel.LoginState.Error -> {
            // Show an error message if login failed
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Login failed. Please try again.",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.error
                )
            }
        }
        LoginViewModel.LoginState.Idle -> {
            // Default UI shown when in Idle state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo Image
                Image(
                    painter = painterResource(id = R.drawable.logo), // Replace with actual logo resource
                    contentDescription = "App Logo",
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Big Heading
                Text(
                    text = "Sarvodaya Constructions",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = { viewModel.login() }, // Trigger login action in ViewModel
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Login", fontSize = 18.sp)
                }
            }
        }
    }
}
