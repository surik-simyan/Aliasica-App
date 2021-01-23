package surik.simyan.aliasica.play.screens

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentTeamsBinding
import surik.simyan.aliasica.play.TeamsRecyclerAdapter

class TeamsFragment : Fragment() {

    private var teamsList: MutableList<TextInputLayout> = mutableListOf()
    lateinit var teamsRecyclerAdapter: TeamsRecyclerAdapter
    lateinit var binding: FragmentTeamsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val getContext = requireContext()
        binding = FragmentTeamsBinding.inflate(inflater,container,false)
        val view = binding.root

        val teamsRecyclerView: RecyclerView = binding.teamsRecyclerView
        teamsRecyclerAdapter = TeamsRecyclerAdapter(getContext,teamsList)


        teamsList.clear()
        teamsList.apply {
            val teamOneField = TextInputLayout(getContext)
            val teamOneText = TextInputEditText(getContext)
            teamOneText.hint = "Team 1"
            teamOneField.addView(teamOneText)

            val teamTwoField = TextInputLayout(getContext)
            val teamTwoText = TextInputEditText(getContext)
            teamTwoText.hint = "Team 2"
            teamTwoField.addView(teamTwoText)

            add(teamOneField)
            add(teamTwoField)

            updateRecycler()
        }

        teamsRecyclerView.apply {
            layoutManager = LinearLayoutManager(getContext)
            adapter = teamsRecyclerAdapter
        }

        binding.teamsFab.setOnClickListener {
            val position = teamsList.size
            val newTeamField = TextInputLayout(getContext)
            newTeamField.endIconMode = TextInputLayout.END_ICON_CUSTOM
            newTeamField.setEndIconDrawable(R.drawable.ic_baseline_remove_circle_24)
            val newTeam = TextInputEditText(getContext)
            newTeam.hint = "Team ${position + 1}"
            newTeamField.addView(newTeam)
            teamsList.add(newTeamField)
            updateRecycler()
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun updateRecycler() {
        teamsRecyclerAdapter.notifyDataSetChanged()
    }
}