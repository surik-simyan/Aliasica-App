package surik.simyan.aliasica.intro.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentThirdScreenBinding
import surik.simyan.aliasica.main.MainActivity

class ThirdScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentThirdScreenBinding.inflate(inflater,container,false)
        val view = binding.root
        // Inflate the layout for this fragment

        binding.finishButton.setOnClickListener {
            val intentMain = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intentMain)
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }
}