package surik.simyan.aliasica.play.game

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import surik.simyan.aliasica.R

class GameStartCountdownFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game_start_countdown, container, false)

        val gameStartCountdownTextView: TextView = view.findViewById(R.id.gameStartCountdownTextView)

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millsUntillFinished: Long) {
                gameStartCountdownTextView.text = ((millsUntillFinished/1000)+1).toString()
            }

            override fun onFinish() {
                findNavController().apply {
                    navigate(R.id.action_gameStartCountdownFragment_to_gamePlayFragment)
                }
            }
        }.start()

        // Inflate the layout for this fragment
        return view
    }
}