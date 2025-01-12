package com.pmgmt.mainapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.projectDetailsFragment,
                R.id.nav_arch_drawings,
                R.id.nav_expenditures,
                R.id.nav_suppliers,
                R.id.nav_site_photos,
                R.id.nav_live_feed,
                R.id.nav_purchases,
                R.id.nav_inventory,
                R.id.nav_meeting_records,
                R.id.nav_important_dates,
                R.id.nav_reports
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    // Handle Logout
                    navController.navigate(R.id.loginFragment)
                    drawerLayout.closeDrawers()
                }
                else -> {
                    // Default behavior for other menu items
                    menuItem.isChecked = true
                    navController.navigate(menuItem.itemId)
                    drawerLayout.closeDrawers()
                }
            }
            true
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}