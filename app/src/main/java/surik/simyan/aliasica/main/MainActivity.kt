package surik.simyan.aliasica.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.ActivityMainBinding
import surik.simyan.aliasica.main.explore.ExploreFragment
import surik.simyan.aliasica.main.home.HomeFragment
import surik.simyan.aliasica.main.settings.SettingsFragment


private lateinit var binding: ActivityMainBinding

val homeFragment = HomeFragment()
val exploreFragment = ExploreFragment()
val settingsFragment = SettingsFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        prepare()
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,homeFragment).commit()
                    supportActionBar?.title = "Home"
                }
                R.id.navigation_explore -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,exploreFragment).commit()
                    supportActionBar?.title = "Explore"
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,settingsFragment).commit()
                    supportActionBar?.title = "Settings"
                }
            }
            true
        }

    }

    private fun prepare() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,homeFragment).commit()
        supportActionBar?.title = "Home"
    }

}