package surik.simyan.aliasica.play.game

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import surik.simyan.aliasica.databinding.FragmentGamePlayBinding


lateinit var binding: FragmentGamePlayBinding
lateinit var viewModel: GameViewModel

class GamePlayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
    : View? {
        binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val view = binding.root

        binding.gamePlayTeamNameTextView.apply {
            if(viewModel.playingTeam == PlayingTeam.TeamOne){
                text = viewModel.teamOneName
            } else {
                text = viewModel.teamTwoName
            }
        }

        if(viewModel.playingTeam == PlayingTeam.TeamOne){
            binding.gamePlayTeamNameTextView.text = viewModel.teamOneName
            viewModel.teamOnePoints().observe(viewLifecycleOwner, Observer { newScore ->
                binding.gamePlayTeamScoreTextView.text = newScore.toString()
            })
        } else {
            binding.gamePlayTeamNameTextView.text = viewModel.teamTwoName
            viewModel.teamTwoPoints().observe(viewLifecycleOwner, Observer { newScore ->
                binding.gamePlayTeamScoreTextView.text = newScore.toString()
            })
        }

        viewModel.remainingTime().observe(viewLifecycleOwner, Observer { newTime ->
            binding.gamePlayTimeSecondsTextView.text = newTime.toString()
        })


        viewModel.startTimer()

        //Word Click Listeners
        binding.apply {
            gamePlayWordOneTextView.setOnClickListener {
                wordClicked(it)
            }
            gamePlayWordTwoTextView.setOnClickListener {
                wordClicked(it)
            }
            gamePlayWordThreeTextView.setOnClickListener {
                wordClicked(it)
            }
            gamePlayWordFourTextView.setOnClickListener {
                wordClicked(it)
            }
            gamePlayWordFiveTextView.setOnClickListener {
                wordClicked(it)
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun wordClicked(view: View) {

        if(view.tag == "Unclicked") {
            view.setBackgroundColor(Color.parseColor("#E2E2E2"))
            view.tag = "Clicked"
            viewModel.addPoint()
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
            view.tag = "Unclicked"
            viewModel.minusPoints()
        }

    }

}