package com.example.soundboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
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

        populateNavigationViewMenu(navigationView)


        //set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    SoundFragment(navigationView.menu.getItem(0).title as String)
                ).commit()
            navigationView.setCheckedItem(navigationView.menu.getItem(0))
        }
    }

    private fun populateNavigationViewMenu(navigationView: NavigationView) {
        val assetsScanner = AssetsScanner(this)
        val dirNames = assetsScanner.listDir("raw")

        for (dir in dirNames)
            navigationView.menu.add(1, Menu.NONE, Menu.NONE, dir)
        //navigationView.menu.add("test thing").setIcon(R.drawable.ic_settings)

        //make group checkable, lets user know which item is selected
        navigationView.menu.setGroupCheckable(1, true, true)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SoundFragment(item.title as String)).commit()

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
