package surik.simyan.aliasica.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.ActivityMainBinding
import surik.simyan.aliasica.main.explore.ExploreFragment
import surik.simyan.aliasica.main.explore.NewWordsetFragment
import surik.simyan.aliasica.main.home.HomeFragment
import surik.simyan.aliasica.main.profile.ProfileFragment
import surik.simyan.aliasica.play.PlayActivity


private lateinit var binding: ActivityMainBinding
private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
public val db = Firebase.firestore

val homeFragment = HomeFragment()
val exploreFragment = ExploreFragment()
val profileFragment = ProfileFragment()
val newWordsetFragment = NewWordsetFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        prepare()

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,homeFragment).commit()
                    binding.fab.show()
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    binding.fab.tag = FabStates.Home
                }
                R.id.navigation_explore -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,exploreFragment).commit()
                    binding.fab.show()
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    binding.fab.setImageResource(R.drawable.ic_baseline_add_circle_outline_24)
                    binding.fab.tag = FabStates.Explore
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,profileFragment).commit()
                    binding.fab.show()
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    binding.fab.setImageResource(R.drawable.ic_baseline_create_24)
                    actionBar?.title = "Profile"
                    binding.fab.tag = FabStates.Profile
                }
                R.id.navigation_settings -> {
                    binding.fab.hide()
                    actionBar?.title = "Settings"

                }
            }
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            true
        }

        binding.fab.setOnClickListener {
            when(it.tag) {
                FabStates.Home -> {
                    val intentPlay = Intent(this, PlayActivity::class.java)
                    startActivity(intentPlay)
                }

                FabStates.Explore -> {
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(R.animator.nav_default_enter_anim,R.animator.nav_default_exit_anim)
                        replace(R.id.fragmentHolder, newWordsetFragment)
                        addToBackStack(null)
                        commit()
                    }
                }
                else -> {

                }
            }
        }

        binding.bottomAppBar.apply {
            setNavigationOnClickListener { bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED }
        }
    }

    override fun onBackPressed() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return
        }
        super.onBackPressed()
    }

    private fun prepare() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder,homeFragment).commit()
        binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomDrawer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        binding.fab.tag = FabStates.Home
    }


}

enum class FabStates {
    Home,Explore,Profile
}