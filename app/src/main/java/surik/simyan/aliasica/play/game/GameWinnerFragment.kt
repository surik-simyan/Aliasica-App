package surik.simyan.aliasica.play.game

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentGameWinnerBinding
import android.os.Handler
import surik.simyan.aliasica.main.MainActivity

class GameWinnerFragment : Fragment() {

    lateinit var binding: FragmentGameWinnerBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        binding = FragmentGameWinnerBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        binding.gameWinnerTextView.text = "Team ${viewModel.winnerTeam} won"

        Handler().postDelayed({
            requireActivity().finish()
        }, 2000)

        // Inflate the layout for this fragment
        return view
    }
}