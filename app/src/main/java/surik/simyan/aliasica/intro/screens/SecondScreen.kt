package surik.simyan.aliasica.intro.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSecondScreenBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.introViewPager)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }
}