package com.example.jobsheet6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolBar)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Set default fragment
        loadFragment(FilmFragment())
        toolbar.title = "Film"

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filmFragment -> {
                    loadFragment(FilmFragment())
                    toolbar.title = "Film"
                    true
                }
                R.id.popularFilmFragment -> {
                    loadFragment(PopularFilmFragment())
                    toolbar.title = "Popular"
                    true
                }
                R.id.userProfileFragment -> {
                    loadFragment(UserProfileFragment())
                    toolbar.title = "Profile"
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
