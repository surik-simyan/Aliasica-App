package surik.simyan.aliasica.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import surik.simyan.aliasica.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()
    }
}