package surik.simyan.aliasica.play.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    //Team One Points
    var _teamOnePoints = MutableLiveData<Int>()
    fun teamOnePoints () : LiveData<Int> {
        return _teamOnePoints
    }

    //Team Two Points
    var _teamTwoPoints = MutableLiveData<Int>()
    fun teamTwoPoints () : LiveData<Int> {
        return _teamTwoPoints
    }

    //Points to play
    var points  = 100F

    //Time of one round
    var time = 45F

    //Team One Name
    var teamOneName = "Team 1"

    //Team Two Name
    var teamTwoName = "Team 2"

    //PlayingTeam
    var playingTeam = PlayingTeam.TeamOne

    //WordsGuessed
    var wordsGuessed = 0

    //Timer
    var timer: CountDownTimer? = null

    //Time Left
    val _remainingTime = MutableLiveData<Int>()
    fun remainingTime () : LiveData<Int> {
        return _remainingTime
    }

    fun addPoint() {
        if(playingTeam == PlayingTeam.TeamOne){
            _teamOnePoints.value?.plus(1)
        } else {
            _teamTwoPoints.value?.plus(1)
        }
        wordsGuessed++
        checkIsAllWordsGuessed()
    }

    fun minusPoints() {
        if(playingTeam == PlayingTeam.TeamOne){
            _teamOnePoints.value?.minus(1)
        } else {
            _teamTwoPoints.value?.minus(1)
        }
        wordsGuessed--
    }

    fun checkIsAllWordsGuessed() {
        if(wordsGuessed == 5){
            updateWords()
        }
    }

    fun updateWords() {
        TODO("Not yet implemented")
    }

    fun startTimer() {
        val timerTime = (time.toLong() * 1000) + 1
        timer = object : CountDownTimer(timerTime, 1000) {
            override fun onTick(millsUntillFinished: Long) {
                _remainingTime.postValue((millsUntillFinished/1000).toInt())
            }

            override fun onFinish() {
                if (playingTeam == PlayingTeam.TeamOne){
                    playingTeam = PlayingTeam.TeamTwo
                } else {
                    playingTeam = PlayingTeam.TeamTwo
                }
            }
        }
        timer?.start()
    }

    fun stopTimer() {
        timer?.cancel()
    }

}

enum class PlayingTeam {
    TeamOne, TeamTwo
}