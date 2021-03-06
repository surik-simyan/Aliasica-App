package surik.simyan.aliasica.play.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

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
    var points : Float = 100F

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

    //isFiveWordsGuessed
    val _isFiveWordsGuessed = MutableLiveData<Boolean>()
    fun isFiveWordsGuessed() : LiveData<Boolean> {
        return _isFiveWordsGuessed
    }

    //isTimeFinished
    val _isTimeFinished = MutableLiveData<Boolean>()
    fun isTimeFinished() : LiveData<Boolean> {
        return _isTimeFinished
    }

    //isGameFinished
    val _isGameFinished = MutableLiveData<Boolean>()
    fun isGameFinished() : LiveData<Boolean> {
        return _isGameFinished
    }

    //Winner team
    var winnerTeam = ""

    //All Words
    var allWordsArrayList = ArrayList<String>()
    var allWords = mutableListOf<String>()

    //Five Words
    val _words = MutableLiveData<List<String>>()
    fun words () : LiveData<List<String>> {
        return _words
    }

    fun fillAllWords() {
        allWords = allWordsArrayList
    }


    fun addPoint() {
        if(playingTeam == PlayingTeam.TeamOne){
            _teamOnePoints.postValue(_teamOnePoints.value?.plus(1))
        } else {
            _teamTwoPoints.postValue(_teamTwoPoints.value?.plus(1))
        }
        wordsGuessed++
        checkIsAllWordsGuessed()
    }

    fun minusPoints() {
        if(playingTeam == PlayingTeam.TeamOne){
            _teamOnePoints.postValue(_teamOnePoints.value?.minus(1))
        } else {
            _teamTwoPoints.postValue(_teamTwoPoints.value?.minus(1))
        }
        wordsGuessed--
    }

    fun checkIsAllWordsGuessed() {
        if(wordsGuessed == 5){
            changeWords()
        }
    }

    fun changeWords() {
        _words.postValue(listOf(allWords[0],allWords[1],allWords[2],allWords[3],allWords[4]))
        _isFiveWordsGuessed.value = true

        allWords.add(allWords.size,allWords[0])
        allWords.removeAt(0)

        allWords.add(allWords.size,allWords[1])
        allWords.removeAt(1)

        allWords.add(allWords.size,allWords[2])
        allWords.removeAt(2)

        allWords.add(allWords.size,allWords[3])
        allWords.removeAt(3)

        allWords.add(allWords.size,allWords[4])
        allWords.removeAt(4)

        _isFiveWordsGuessed.postValue(false)
        wordsGuessed = 0
    }

    fun gameFinished(){
        if (playingTeam == PlayingTeam.TeamOne){
            if(_teamOnePoints.value!! >= points){
                winnerTeam = teamOneName
                _isGameFinished.postValue(true)
            }
        } else {
            if(_teamTwoPoints.value!! >= points){
                winnerTeam = teamTwoName
                _isGameFinished.postValue(true)
            }
        }
        _isTimeFinished.postValue(true)
        if (playingTeam == PlayingTeam.TeamOne){
            playingTeam = PlayingTeam.TeamTwo
        } else {
            playingTeam = PlayingTeam.TeamTwo
        }
    }

    fun startTimer() {
        val timerTime = (time.toLong() * 1000)
        timer = object : CountDownTimer(timerTime, 1000) {
            override fun onTick(millsUntillFinished: Long) {
                _remainingTime.postValue(((millsUntillFinished/1000) + 1).toInt())
            }

            override fun onFinish() {
                gameFinished()
            }
        }
        timer?.start()
    }

    fun stopTimer() {
        timer?.cancel()
    }

    fun shuffleWords ()
    {
        allWords.shuffle()
    }

}

enum class PlayingTeam {
    TeamOne, TeamTwo
}