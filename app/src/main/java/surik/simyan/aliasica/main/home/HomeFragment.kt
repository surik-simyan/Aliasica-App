package surik.simyan.aliasica.main.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.database.DownloadedWordsetDatabase
import surik.simyan.aliasica.databinding.FragmentHomeBinding
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.HomeWordsetModel

class HomeFragment : Fragment() {

    private var homeWordsetList: MutableList<HomeWordsetModel> = mutableListOf<HomeWordsetModel>()
    lateinit var binding : FragmentHomeBinding
    lateinit var homeWordsetRecyclerAdapter: HomeRecyclerAdapter
    lateinit var downloadedDB: DownloadedWordsetDatabase

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        val view = binding.root

        downloadedDB = DownloadedWordsetDatabase.getInstance(requireContext())

        val wordsetHomeRecyclerView: RecyclerView = binding.homeRecyclerView
        homeWordsetRecyclerAdapter = HomeRecyclerAdapter(requireContext(),homeWordsetList)

        wordsetHomeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordsetHomeRecyclerView.adapter = homeWordsetRecyclerAdapter

        homeWordsetList.clear()
        getWordsets()
        updateRecycler()


        // Inflate the layout for this fragment
        return view
    }

    private fun getWordsets() {
        GlobalScope.launch (Dispatchers.IO) {
            downloadedDB.getWordsetDao().getAllDownloaded()
        }
        updateRecycler()
    }


    private fun updateRecycler() {
        homeWordsetRecyclerAdapter.notifyDataSetChanged()
    }
}