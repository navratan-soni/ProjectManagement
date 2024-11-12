package com.pmgmt.mainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.pmgmt.mainapp.data.network.NetworkModule
import com.pmgmt.mainapp.data.repository.ArchitectRepository
import com.pmgmt.mainapp.navigation.AppNavHost
import com.pmgmt.mainapp.presentation.viewmodel.LoginViewModel
import com.pmgmt.mainapp.presentation.viewmodel.ProjectDetailsViewModel
import com.pmgmt.mainapp.presentation.viewmodel.factory.LoginViewModelFactory
import com.pmgmt.mainapp.presentation.viewmodel.factory.ProjectDetailsViewModelFactory
import com.pmgmt.mainapp.themes.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = NetworkModule.provideApiService(applicationContext)
        val architectRepository = ArchitectRepository(apiService)

        val loginViewModel: LoginViewModel by lazy {
            ViewModelProvider(this, LoginViewModelFactory(architectRepository))
                .get(LoginViewModel::class.java)
        }

        val projectDetailsViewModel: ProjectDetailsViewModel by lazy {
            ViewModelProvider(this, ProjectDetailsViewModelFactory(architectRepository))
                .get(ProjectDetailsViewModel::class.java)
        }
        setContent {
            AppTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController,
                    loginViewModel = loginViewModel,
                    projectDetailsViewModel = projectDetailsViewModel)
            }
        }
    }
}