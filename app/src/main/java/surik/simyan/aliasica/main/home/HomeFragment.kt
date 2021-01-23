package surik.simyan.aliasica.main.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import surik.simyan.aliasica.R

class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}