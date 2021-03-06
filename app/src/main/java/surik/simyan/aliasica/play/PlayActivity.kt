package surik.simyan.aliasica.play

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.ActivityMainBinding
import surik.simyan.aliasica.databinding.ActivityPlayBinding
import surik.simyan.aliasica.play.game.GameViewModel

lateinit var binding: ActivityPlayBinding

lateinit var viewModel: GameViewModel

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val words = intent.getStringArrayListExtra("words")
        viewModel.allWordsArrayList = words!!
        viewModel.shuffleWords()
        if(viewModel._teamOnePoints.value == null){
            viewModel._teamOnePoints.value = 0
        }

        if(viewModel._teamTwoPoints.value == null){
            viewModel._teamTwoPoints.value = 0
        }
    }
}