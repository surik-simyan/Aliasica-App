package surik.simyan.aliasica.main.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import surik.simyan.aliasica.R
import surik.simyan.aliasica.database.DownloadedWordsetDatabase
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.toHomeWordset
import java.util.*

class ExploreRecyclerAdapter(val context: Context, private var elements: List<ExploreWordsetModel>, val exploreWordsetButtonsClickListener: ExploreWordsetButtonsClickListener) : RecyclerView.Adapter<ExploreViewHolder>() {

    var downloadedDB = DownloadedWordsetDatabase.getInstance(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.explore_wordset_card_view,parent,false)
        return  ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
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
            exploreWordsetButtonsClickListener.onPlayClick(item)
        }

        holder.downloadButton?.setOnClickListener {
            exploreWordsetButtonsClickListener.onDownloadClick(item)
        }

    }

    override fun getItemCount(): Int {
        return elements.size
    }

}

class ExploreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val creator: TextView?
    val language: TextView?
    val name: TextView?
    val tags: ChipGroup?
    val playButton: Button?
    val downloadButton: Button?

    init {
        creator = view.findViewById(R.id.exploreWordsetCreatorTextView)
        language = view.findViewById(R.id.exploreWordsetLanguageTextView)
        name = view.findViewById(R.id.exploreWordsetNameTextView)
        tags = view.findViewById(R.id.exploreWordsetTagsChipGroup)
        playButton = view.findViewById(R.id.exploreWordsetPlayButton)
        downloadButton = view.findViewById(R.id.exploreWordsetDownloadButton)
    }
}

interface ExploreWordsetButtonsClickListener {
    fun onDownloadClick(item: ExploreWordsetModel)
    fun onPlayClick(item: ExploreWordsetModel)
}
