package surik.simyan.aliasica.play.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentPointsBinding
import surik.simyan.aliasica.play.game.GameViewModel
import surik.simyan.aliasica.play.game.viewModel

class PointsFragment : Fragment() {

    lateinit var binding : FragmentPointsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointsBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)


        binding.pointsSlider.addOnChangeListener { slider, value, fromUser -> /* `value` is the argument you need */

            viewModel.points = value
            Toast.makeText(requireContext(),viewModel.points.toString(),Toast.LENGTH_SHORT).show()
        }


        // Inflate the layout for this fragment
        return view
    }
}