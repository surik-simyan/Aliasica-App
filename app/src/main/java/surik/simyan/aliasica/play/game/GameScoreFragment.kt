package surik.simyan.aliasica.play.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentGameScoreBinding

class GameScoreFragment : Fragment() {

    lateinit var binding: FragmentGameScoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //
            }
        })
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameScoreBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        viewModel.teamOnePoints().observe(viewLifecycleOwner, Observer {
            binding.gameTeamOneScoreTextView.text = it.toString()
        })

        viewModel.teamTwoPoints().observe(viewLifecycleOwner, Observer {
            binding.gameTeamTwoScoreTextView.text = it.toString()
        })

        viewModel.isGameFinished().observe(viewLifecycleOwner, Observer { isGameFinished ->
            if(isGameFinished == true) {
                findNavController().navigate(R.id.action_gameScoreFragment_to_gameWinnerFragment)
            }
        })

        viewModel.allWords.shuffle()

        binding.gameTeamOneNameTextView.text = viewModel.teamOneName
        binding.gameTeamTwoNameTextView.text = viewModel.teamTwoName
        if(viewModel.playingTeam == PlayingTeam.TeamOne){
            binding.gameTeamStartTextView.text = "${viewModel.teamOneName}\nStart"
        } else {
            binding.gameTeamStartTextView.text = "${viewModel.teamTwoName}\nStart"
        }

        Toast.makeText(context,viewModel.points.toString(),Toast.LENGTH_SHORT).show()

        binding.gameTeamStartCardView.setOnClickListener {
            findNavController().navigate(R.id.action_gameScoreFragment_to_gameStartCountdownFragment)
        }

        return view
    }


}