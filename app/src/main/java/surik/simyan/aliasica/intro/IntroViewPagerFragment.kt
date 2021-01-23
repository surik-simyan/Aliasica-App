package surik.simyan.aliasica.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import surik.simyan.aliasica.databinding.FragmentIntroViewPagerBinding
import surik.simyan.aliasica.intro.screens.FirstScreen
import surik.simyan.aliasica.intro.screens.SecondScreen
import surik.simyan.aliasica.intro.screens.ThirdScreen

class IntroViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentIntroViewPagerBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.introViewPager.isUserInputEnabled = false

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = IntroViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.introViewPager.adapter = adapter
        return view
    }

}