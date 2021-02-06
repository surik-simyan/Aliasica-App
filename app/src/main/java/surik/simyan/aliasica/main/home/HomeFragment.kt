package surik.simyan.aliasica.main.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentHomeBinding
import surik.simyan.aliasica.main.explore.ExploreRecyclerAdapter

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var wordsetRecyclerAdapter: HomeRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        val view = binding.root

        val wordsetRecyclerView: RecyclerView = binding.homeRecyclerView


        // Inflate the layout for this fragment
        return view
    }
}