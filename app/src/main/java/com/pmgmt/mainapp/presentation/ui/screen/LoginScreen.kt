package com.pmgmt.mainapp.presentation.ui.screen

import androidx.compose.runtime.Composable
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.data.model.Project


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (List<Project>) -> Unit
) {
   /* val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    // Collect navigation event from SharedFlow
    LaunchedEffect(Unit) {
        viewModel.navigateToProjects.collect { projects ->
            onLoginSuccess(projects)
        }
    }

    when (loginState) {
        is LoginViewModel.LoginState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Logging in...")
            }
        }
        is LoginViewModel.LoginState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Login failed. Please try again.",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.error
                )
            }
        }
        LoginViewModel.LoginState.Idle -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Sarvodaya Constructions",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { viewModel.login() },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Login", fontSize = 18.sp)
                }
            }
        }

        is LoginViewModel.LoginState.Success -> {
            // Handle Success minimally since navigation is handled separately
        }
    }*/
}
