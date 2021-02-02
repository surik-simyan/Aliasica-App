package surik.simyan.aliasica.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentSigninBinding

class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSigninBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.signInMaterialButton.setOnClickListener {

        }

        binding.facebookSignInMaterialButton.setOnClickListener {

        }

        binding.googleSignInMaterialButton.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()


        }

        binding.createAccountMaterialButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }



        // Inflate the layout for this fragment
        return view
    }
}