package surik.simyan.aliasica.play

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import surik.simyan.aliasica.R

class TeamsRecyclerAdapter(val context: Context, private var elements: MutableList<TextInputLayout>) : RecyclerView.Adapter<TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_name_field,parent,false)
        return  TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val item = elements[position]
        holder.teamsEditText?.text = item.editText?.text
        holder.teamsInputLayout?.setEndIconOnClickListener {
            if(itemCount > 3){
                elements.drop(position)
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int {
        return elements.size
    }



}

class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val teamsInputLayout: TextInputLayout?
    val teamsEditText : TextInputEditText?


    init {
        teamsInputLayout = view.findViewById(R.id.teamsTextField)
        teamsEditText = view.findViewById(R.id.teamsTextInputEditText)
    }
}