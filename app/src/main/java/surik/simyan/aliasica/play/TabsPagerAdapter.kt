package surik.simyan.aliasica.play

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import surik.simyan.aliasica.play.screens.TimeFragment
import surik.simyan.aliasica.play.screens.PointsFragment
import surik.simyan.aliasica.play.screens.TeamsFragment

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Points Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "PointsFragment Fragment")
                val pointsFragment = PointsFragment()
                pointsFragment.arguments = bundle
                return pointsFragment
            }
            1 -> {
                // # Duration Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Duration Fragment")
                val durationFragment = TimeFragment()
                durationFragment.arguments = bundle
                return durationFragment
            }
            2 -> {
                // # Teams Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Teams Fragment")
                val teamsFragment = TeamsFragment()
                teamsFragment.arguments = bundle
                return teamsFragment
            }
            else -> return PointsFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}