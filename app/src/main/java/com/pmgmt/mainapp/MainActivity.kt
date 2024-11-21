package com.pmgmt.mainapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


/*
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
}*/

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}