package surik.simyan.aliasica.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import surik.simyan.aliasica.R
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import surik.simyan.aliasica.main.MainActivity

private lateinit var mAuth: FirebaseAuth

class SplashFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            //if (user != null) {
                val intentMain = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intentMain)
            //}
            /*if(onBoardingFinished())
            {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_signinFragment)
            }*/
        }, 1000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
    
}