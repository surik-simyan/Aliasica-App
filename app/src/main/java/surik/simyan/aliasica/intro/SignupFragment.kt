package surik.simyan.aliasica.intro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentSignupBinding

private lateinit var auth: FirebaseAuth

class SignupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignupBinding.inflate(inflater,container,false)

        auth = Firebase.auth



        return binding.root
    }
}