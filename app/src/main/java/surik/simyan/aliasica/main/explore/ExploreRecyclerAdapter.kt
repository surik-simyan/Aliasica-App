package surik.simyan.aliasica.main.explore

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import surik.simyan.aliasica.R
import surik.simyan.aliasica.play.PlayActivity
import java.util.*

class ExploreRecyclerAdapter(val context: Context, private var elements: List<FirebaseWordsetModel>) : RecyclerView.Adapter<ExploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.firebase_wordset_card_view,parent,false)
        return  ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val item = elements[position]
        holder.creator?.text = item.creator
        holder.language?.text = item.language
        holder.name?.text = item.name
        holder.rating?.text = item.rating.toString()
        holder.stars?.rating = item.rating!!
        holder.tags?.removeAllViews()
        item.tags?.forEach {
            val myChip = Chip(context)
            myChip.text = it.capitalize(Locale.ROOT)
            holder.tags?.addView(myChip)
        }
        holder.playButton?.setOnClickListener {
            val intentPlay = Intent(context, PlayActivity::class.java)
            intentPlay.putExtra("numberOfTabs",3)
            intentPlay.putStringArrayListExtra ("words", item.words as ArrayList<String>?)
            context.startActivity(intentPlay)
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
    val rating: TextView?
    val stars: RatingBar?
    val tags: ChipGroup?
    val playButton: Button?
    val downloadButton: Button?

    init {
        creator = view.findViewById(R.id.wordsetCreatorTextView)
        language = view.findViewById(R.id.wordsetLanguageTextView)
        name = view.findViewById(R.id.wordsetNameTextView)
        rating = view.findViewById(R.id.wordsetRatingTextView)
        stars = view.findViewById(R.id.wordsetRatingBar)
        tags = view.findViewById(R.id.wordsetTagsChipGroup)
        playButton = view.findViewById(R.id.wordsetPlayButton)
        downloadButton = view.findViewById(R.id.wordsetDownloadButton)
    }
}