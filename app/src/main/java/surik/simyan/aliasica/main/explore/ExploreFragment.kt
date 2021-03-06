package surik.simyan.aliasica.main.explore

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.database.DownloadedWordsetDatabase
import surik.simyan.aliasica.databinding.FragmentExploreBinding
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.toHomeWordset
import surik.simyan.aliasica.play.PlayActivity
import java.util.ArrayList

class ExploreFragment : Fragment(), ExploreWordsetButtonsClickListener {

    private var exploreWordsetList: MutableList<ExploreWordsetModel> = mutableListOf<ExploreWordsetModel>()
    lateinit var exploreWordsetRecyclerAdapter: ExploreRecyclerAdapter
    lateinit var binding: FragmentExploreBinding
    val db = Firebase.firestore
    lateinit var downloadedDB: DownloadedWordsetDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        downloadedDB = DownloadedWordsetDatabase.getInstance(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater)
        val view = binding.root

        val wordsetRecyclerView: RecyclerView = binding.wordsetExploreRecyclerView
        exploreWordsetRecyclerAdapter = ExploreRecyclerAdapter(requireContext(),exploreWordsetList,this)

        wordsetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordsetRecyclerView.adapter = exploreWordsetRecyclerAdapter

        binding.wordsetExploreSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE)
        binding.wordsetExploreSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

        exploreWordsetList.clear()
        getWordsets()
        updateRecycler()

        binding.wordsetExploreSwipeRefreshLayout.setOnRefreshListener {
            exploreWordsetList.clear()
            getWordsets()
            updateRecycler()
        }

        return view
    }

    private fun getWordsets() {
        GlobalScope.launch (Dispatchers.IO) {
            db.collection("wordlists")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val wordset = document.toObject(ExploreWordsetModel::class.java)
                        exploreWordsetList.add(wordset)
                        updateRecycler()
                    }
                    binding.wordsetExploreSwipeRefreshLayout.isRefreshing = false
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(),"Huh, seems you are not connected to internet",Toast.LENGTH_SHORT)
                    binding.wordsetExploreSwipeRefreshLayout.isRefreshing = false
                }
        }
    }

    private fun updateRecycler() {
        exploreWordsetRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onDownloadClick(item: ExploreWordsetModel) {
        GlobalScope.launch (Dispatchers.IO) {
            downloadedDB.getWordsetDao().insert(item.toHomeWordset())
        }
        val bottomNav: BottomNavigationView = requireActivity().findViewById(R.id.bottomNav)
        Snackbar.make(binding.root,"Wordset ${item.name} successfully downloaded",Snackbar.LENGTH_SHORT).apply {
            setAnchorView(bottomNav)
        }.show()
    }

    override fun onPlayClick(item: ExploreWordsetModel) {
        val intentPlay = Intent(context, PlayActivity::class.java)
        intentPlay.putExtra("numberOfTabs",3)
        intentPlay.putStringArrayListExtra ("words", item.words as ArrayList<String>?)
        requireContext().startActivity(intentPlay)
    }

}