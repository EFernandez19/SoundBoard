package com.example.soundboard

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.soundboard.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create drawer
        drawer = binding.drawerLayout

        //Create toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        //create navigation view
        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        //set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BellFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_bell)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //determine what fragment is shown
        when (item.getItemId()) {
            R.id.nav_bell -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BellFragment()).commit()
            R.id.nav_applause -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DefaultSoundsFragment()).commit()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
