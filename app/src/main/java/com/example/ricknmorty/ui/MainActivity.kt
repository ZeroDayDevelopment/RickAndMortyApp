package com.example.ricknmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.ricknmorty.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val NavHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val NavController = NavHost.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNav, NavController)

    }
}

