package surik.simyan.aliasica.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.ActivityMainBinding
import surik.simyan.aliasica.main.explore.ExploreFragment
import surik.simyan.aliasica.main.home.HomeFragment
import surik.simyan.aliasica.main.settings.SettingsFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //private val homeFragment = HomeFragment()
    //private val exploreFragment = ExploreFragment()
    //private val settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Prepare Screen
        supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, HomeFragment()).commit()
        supportActionBar?.title = "Home"


        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,HomeFragment()).commit()
                    supportActionBar?.title = "Home"
                }
                R.id.navigation_explore -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,ExploreFragment()).commit()
                    supportActionBar?.title = "Explore"
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,SettingsFragment()).commit()
                    supportActionBar?.title = "Settings"
                }
            }
            true
        }

    }
}