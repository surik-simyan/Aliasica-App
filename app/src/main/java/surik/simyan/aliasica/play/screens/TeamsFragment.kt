package surik.simyan.aliasica.play.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.slider.Slider
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentTeamsBinding
import surik.simyan.aliasica.play.TabsFragment
import surik.simyan.aliasica.play.game.GameScoreFragment
import surik.simyan.aliasica.play.game.GameViewModel
import surik.simyan.aliasica.play.game.viewModel

class TeamsFragment : Fragment() {

    lateinit var binding: FragmentTeamsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val getContext = requireContext()
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        binding.teamsStartFab.setOnClickListener {
            val teamOneName: String = binding.teamOneTextInputEditText.text.toString()
            val teamTwoName: String = binding.teamTwoTextInputEditText.text.toString()
            if(teamOneName.trim().isNotEmpty()){
                viewModel.teamOneName = teamOneName
            }
            if(teamTwoName.trim().isNotEmpty()){
                viewModel.teamTwoName = teamTwoName
            }
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            view.findNavController().navigate(R.id.action_tabsFragment_to_gameScoreFragment)
        }


        // Inflate the layout for this fragment
        return view
    }
}