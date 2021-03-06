package surik.simyan.aliasica.main.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import surik.simyan.aliasica.R
import surik.simyan.aliasica.database.DownloadedWordsetDatabase
import surik.simyan.aliasica.databinding.FragmentHomeBinding
import surik.simyan.aliasica.main.home.HomeWordsetButtonsClickListener
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.HomeWordsetModel
import surik.simyan.aliasica.play.PlayActivity
import java.util.ArrayList

class HomeFragment : Fragment() , HomeWordsetButtonsClickListener {

    private var homeWordsetList: MutableList<HomeWordsetModel> = mutableListOf<HomeWordsetModel>()
    lateinit var binding : FragmentHomeBinding
    lateinit var homeWordsetRecyclerAdapter: HomeRecyclerAdapter
    lateinit var downloadedDB: DownloadedWordsetDatabase


    override fun onAttach(context: Context) {
        super.onAttach(context)
        downloadedDB = DownloadedWordsetDatabase.getInstance(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        val view = binding.root

        val wordsetHomeRecyclerView: RecyclerView = binding.homeRecyclerView
        homeWordsetRecyclerAdapter = HomeRecyclerAdapter(requireContext(),homeWordsetList,this)

        binding.wordsetHomeSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE)
        binding.wordsetHomeSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

        wordsetHomeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordsetHomeRecyclerView.adapter = homeWordsetRecyclerAdapter


        resettingView()
        binding.wordsetHomeSwipeRefreshLayout.setOnRefreshListener {
            resettingView()
        }



        // Inflate the layout for this fragment
        return view
    }

    fun resettingView() {
        homeWordsetList.clear()
        getWordsets()
        updateRecycler()
    }

    private fun getWordsets() {
        GlobalScope.launch (Dispatchers.IO) {
            downloadedDB.getWordsetDao().getAllDownloaded().forEach {
                homeWordsetList.add(it)
            }
        }
        updateRecycler()
        binding.wordsetHomeSwipeRefreshLayout.isRefreshing = false
    }


    private fun updateRecycler() {
        homeWordsetRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onDeleteClick(item: HomeWordsetModel) {
        GlobalScope.launch (Dispatchers.IO) {
            downloadedDB.getWordsetDao().delete(item)
        }
        resettingView()
    }

    override fun onPlayClick(item: HomeWordsetModel) {
        val intentPlay = Intent(context, PlayActivity::class.java)
        intentPlay.putExtra("numberOfTabs",3)
        intentPlay.putStringArrayListExtra ("words", item.words as ArrayList<String>?)
        requireContext().startActivity(intentPlay)
    }
}