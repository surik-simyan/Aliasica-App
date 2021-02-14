package surik.simyan.aliasica.main.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import surik.simyan.aliasica.R
import surik.simyan.aliasica.databinding.FragmentNewWordsetBinding

class NewWordsetFragment : Fragment() {

    lateinit var binding: FragmentNewWordsetBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    :View? {
        val getContext = requireContext()
        binding = FragmentNewWordsetBinding.inflate(inflater,container,false)

        //Language
        val items = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(getContext, R.layout.language_list_item, items)
        (binding.newWordsetLanguageTextField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        //Tags


        // Inflate the layout for this fragment
        return binding.root
    }


}