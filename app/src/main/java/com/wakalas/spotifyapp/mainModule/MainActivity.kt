package com.wakalas.spotifyapp.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpBottomNav()
    }

    private fun setUpBottomNav()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        mNavController = navHostFragment.navController
        setupWithNavController(bottomNavigationView, mNavController)
    }
}
