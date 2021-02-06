package surik.simyan.aliasica.play.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentPointsBinding
import surik.simyan.aliasica.databinding.FragmentTimeBinding
import surik.simyan.aliasica.play.game.GameViewModel

class TimeFragment : Fragment() {

    lateinit var binding : FragmentTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTimeBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)


        binding.timeSlider.addOnChangeListener { slider, value, fromUser -> /* `value` is the argument you need */
            viewModel.time = value
        }

        // Inflate the layout for this fragment
        return view
    }
}