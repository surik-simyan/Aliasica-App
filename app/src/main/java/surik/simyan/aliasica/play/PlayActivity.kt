package surik.simyan.aliasica.play

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.ActivityMainBinding
import surik.simyan.aliasica.databinding.ActivityPlayBinding

lateinit var binding: ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        val numberOfTabs = 3

        binding.playTabLayout.tabMode = TabLayout.MODE_FIXED
        binding.playTabLayout.isInlineLabel = true

        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.playViewPager.adapter = adapter
        binding.playViewPager.isUserInputEnabled = true

        TabLayoutMediator(binding.playTabLayout, binding.playViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Points"
                    tab.setIcon(R.drawable.ic_baseline_check_circle_24)
                }
                1 -> {
                    tab.text = "Time"
                    tab.setIcon(R.drawable.ic_baseline_alarm_24)

                }
                2 -> {
                    tab.text = "Teams"
                    tab.setIcon(R.drawable.ic_baseline_group_24)
                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()



    }
}