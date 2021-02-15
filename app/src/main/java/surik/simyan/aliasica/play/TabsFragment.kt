package surik.simyan.aliasica.play

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentTabsBinding
import surik.simyan.aliasica.play.game.GameViewModel


class TabsFragment : Fragment() {

    lateinit var binding: FragmentTabsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentTabsBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        val numberOfTabs = 3

        binding.playTabLayout.tabMode = TabLayout.MODE_AUTO
        binding.playTabLayout.isInlineLabel = true

        val adapter = TabsPagerAdapter(requireActivity().supportFragmentManager, lifecycle, numberOfTabs)
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
        viewModel._words.postValue(requireActivity().intent.getStringArrayListExtra("words"))


        // Inflate the layout for this fragment
        return view
    }

}