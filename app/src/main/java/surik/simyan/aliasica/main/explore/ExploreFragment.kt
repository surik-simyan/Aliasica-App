package surik.simyan.aliasica.main.explore

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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentExploreBinding
import surik.simyan.aliasica.models.ExploreWordsetModel

class ExploreFragment : Fragment() {

    private var exploreWordsetList: MutableList<ExploreWordsetModel> = mutableListOf<ExploreWordsetModel>()
    lateinit var exploreWordsetRecyclerAdapter: ExploreRecyclerAdapter
    lateinit var binding: FragmentExploreBinding
    val db = Firebase.firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater)
        val view = binding.root

        val wordsetRecyclerView: RecyclerView = binding.wordsetExploreRecyclerView
        exploreWordsetRecyclerAdapter = ExploreRecyclerAdapter(requireContext(),exploreWordsetList)

        wordsetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordsetRecyclerView.adapter = exploreWordsetRecyclerAdapter

        binding.wordsetExploreSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        binding.wordsetExploreSwipeRefreshLayout.setColorSchemeColors(Color.WHITE)

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
}