package surik.simyan.aliasica.main.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import surik.simyan.aliasica.main.explore.FirebaseWordsetModel

class HomeRecyclerAdapter(val context: Context, private var elements: List<FirebaseWordsetModel>) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}