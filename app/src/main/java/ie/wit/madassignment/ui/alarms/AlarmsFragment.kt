package ie.wit.madassignment.ui.alarms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ie.wit.madassignment.R

class AlarmsFragment : Fragment() {

    private lateinit var alarmsViewModel: AlarmsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        alarmsViewModel =
                ViewModelProviders.of(this).get(AlarmsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alarms, container, false)
        val textView: TextView = root.findViewById(R.id.text_alarms)
        alarmsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}