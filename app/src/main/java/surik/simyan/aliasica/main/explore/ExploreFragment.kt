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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentExploreBinding
import surik.simyan.aliasica.main.db

class ExploreFragment : Fragment() {

    private var firebaseWordsetList: MutableList<FirebaseWordsetModel> = mutableListOf<FirebaseWordsetModel>()
    lateinit var wordsetRecyclerAdapter: ExploreRecyclerAdapter
    lateinit var binding: FragmentExploreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater)
        val view = binding.root

        val wordsetRecyclerView: RecyclerView = binding.wordsetRecyclerView
        wordsetRecyclerAdapter = ExploreRecyclerAdapter(requireContext(),firebaseWordsetList)

        wordsetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        wordsetRecyclerView.adapter = wordsetRecyclerAdapter

        binding.wordsetSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        binding.wordsetSwipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        firebaseWordsetList.clear()
        getWordsets()
        updateRecycler()

        binding.wordsetSwipeRefreshLayout.setOnRefreshListener {
            firebaseWordsetList.clear()
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
                        firebaseWordsetList.add(document.toObject(FirebaseWordsetModel::class.java))
                        updateRecycler()
                    }
                    binding.wordsetSwipeRefreshLayout.isRefreshing = false
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(),"Huh, seems you are not connected to internet",Toast.LENGTH_SHORT)
                    binding.wordsetSwipeRefreshLayout.isRefreshing = false
                }
        }
    }

    private fun updateRecycler() {
        wordsetRecyclerAdapter.notifyDataSetChanged()
    }
}