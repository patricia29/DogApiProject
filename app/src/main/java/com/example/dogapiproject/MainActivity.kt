package com.example.dogapiproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dogapiproject.fragment.DogImageFragment
import com.example.dogapiproject.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity  : AppCompatActivity() {
    // Define DogImagesFragment and SearchFragment as previously shown
    private val dogImagesFragment = DogImageFragment()
    private val searchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_dog_images -> replaceFragment(dogImagesFragment)
                R.id.action_search -> replaceFragment(searchFragment)
            }
            true
        }

        // Set the default fragment when the activity starts
        if (savedInstanceState == null) {
            bottomNavigation.selectedItemId = R.id.action_dog_images
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}