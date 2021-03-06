package surik.simyan.aliasica.play.game

import android.content.Context
import android.content.SharedPreferences
import surik.simyan.aliasica.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import surik.simyan.aliasica.databinding.FragmentGamePlayBinding


lateinit var binding: FragmentGamePlayBinding
lateinit var viewModel: GameViewModel
lateinit var sharedPreferences: SharedPreferences
lateinit var wordGuessedSoundPool: SoundPool
private val soundId = 1


class GamePlayFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                returnToScoreFragment()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        wordGuessedSoundPool = SoundPool.Builder().build()
        wordGuessedSoundPool.load(context,R.raw.game_word_guessed, 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
    : View? {
        binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
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

        viewModel.isFiveWordsGuessed().observe(viewLifecycleOwner, Observer { isGuessed ->
            if(isGuessed == true){
                resetingWordsView()
            }
        })

        viewModel.isTimeFinished().observe(viewLifecycleOwner, Observer { isTimeFinished ->
            if(isTimeFinished == true) {
                returnToScoreFragment()
            }
        })

        viewModel.words().observe(viewLifecycleOwner, Observer { newWordsList ->
            binding.apply {
                gamePlayWordOneTextView.text = newWordsList[0]
                gamePlayWordTwoTextView.text = newWordsList[1]
                gamePlayWordThreeTextView.text = newWordsList[2]
                gamePlayWordFourTextView.text = newWordsList[3]
                gamePlayWordFiveTextView.text = newWordsList[4]
            }

        })

        viewModel.startTimer()

        viewModel.changeWords()

        resetingWordsView()

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

    private fun returnToScoreFragment() {
        findNavController().navigate(R.id.action_gamePlayFragment_to_gameScoreFragment)
        viewModel._isTimeFinished.postValue(false)
        viewModel._isFiveWordsGuessed.postValue(false)
        viewModel.wordsGuessed = 0
    }

    fun resetingWordsView (){
        binding.apply {
            gamePlayWordOneTextView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gamePlayWordOneTextView.tag = "Unclicked"

            gamePlayWordTwoTextView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gamePlayWordTwoTextView.tag = "Unclicked"

            gamePlayWordThreeTextView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gamePlayWordThreeTextView.tag = "Unclicked"

            gamePlayWordFourTextView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gamePlayWordFourTextView.tag = "Unclicked"

            gamePlayWordFiveTextView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            gamePlayWordFiveTextView.tag = "Unclicked"
        }
    }

    private fun wordClicked(view: View) {

        if(!sharedPreferences.getBoolean("game_mode",false)){
            if(view.tag == "Unclicked") {
                view.setBackgroundColor(Color.parseColor("#E2E2E2"))
                view.tag = "Clicked"
                viewModel.addPoint()
            } else {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                view.tag = "Unclicked"
                viewModel.minusPoints()
            }
        } else {
            if(view.tag == "Unclicked") {
                view.setBackgroundColor(Color.parseColor("#E2E2E2"))
                view.tag = "Clicked"
                wordGuessedSoundPool.play(soundId, 1F, 1F, 0, 0, 1F)
                viewModel.addPoint()
            } else {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                view.tag = "Unclicked"
                viewModel.minusPoints()
            }
        }

    }
}