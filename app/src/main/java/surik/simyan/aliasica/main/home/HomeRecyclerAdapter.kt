package surik.simyan.aliasica.main.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.database.DownloadedWordsetDatabase
import surik.simyan.aliasica.main.explore.ExploreViewHolder
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.HomeWordsetModel
import surik.simyan.aliasica.play.PlayActivity
import java.util.*

class HomeRecyclerAdapter(val context: Context, private var elements: List<HomeWordsetModel>, val homeWordsetButtonsClickListener : HomeWordsetButtonsClickListener) : RecyclerView.Adapter<HomeViewHolder>() {

    var downloadedDB = DownloadedWordsetDatabase.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_wordset_card_view,parent,false)
        return  HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = elements[position]
        holder.creator?.text = item.creator
        holder.language?.text = item.language
        holder.name?.text = item.name
        holder.tags?.removeAllViews()
        item.tags?.forEach {
            val myChip = Chip(context)
            myChip.text = it.capitalize(Locale.ROOT)
            holder.tags?.addView(myChip)
        }

        holder.playButton?.setOnClickListener {
            homeWordsetButtonsClickListener.onPlayClick(item)
        }

        holder.deleteButton?.setOnClickListener {
            homeWordsetButtonsClickListener.onDeleteClick(item)
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val creator: TextView?
    val language: TextView?
    val name: TextView?
    val tags: ChipGroup?
    val playButton: Button?
    val deleteButton: Button?

    init {
        creator = view.findViewById(R.id.homeWordsetCreatorTextView)
        language = view.findViewById(R.id.homeWordsetLanguageTextView)
        name = view.findViewById(R.id.homeWordsetNameTextView)
        tags = view.findViewById(R.id.homeWordsetTagsChipGroup)
        playButton = view.findViewById(R.id.homeWordsetPlayButton)
        deleteButton = view.findViewById(R.id.homeWordsetDeleteButton)
    }
}

interface HomeWordsetButtonsClickListener {
    fun onDeleteClick(item: HomeWordsetModel)
    fun onPlayClick(item: HomeWordsetModel)
}