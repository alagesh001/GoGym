package com.oxad.gogym.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oxad.gogym.R
import com.oxad.gogym.fragment.HomeFragment
import com.oxad.gogym.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Load HomeFragment by default
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        if (savedInstanceState == null) {
            openFragment(homeFragment)
        }
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    openFragment(homeFragment)
                    true
                }
                R.id.menu_profile -> {
                    openFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }



    private fun openFragment(fragment: Fragment) {
        Log.w("checkfrag","Yes $fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}