package ie.wit.madassignment.ui.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ie.wit.madassignment.MainActivity
import ie.wit.madassignment.R

class StepsFragment : Fragment() {

    private lateinit var stepsViewModel: StepsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        stepsViewModel =
                ViewModelProviders.of(this).get(StepsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_steps, container, false)
        val textView: TextView = root.findViewById(R.id.text_steps)

        val button = root.findViewById<Button>(R.id.button);

        button.setOnClickListener {
            (activity as MainActivity).readData()
        }

        stepsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}