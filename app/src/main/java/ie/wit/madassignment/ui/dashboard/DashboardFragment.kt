package ie.wit.madassignment.ui.dashboard

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
import com.firebase.ui.auth.AuthUI
import ie.wit.madassignment.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val button = root.findViewById<Button>(R.id.btn_logout);

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        button.setOnClickListener {
//            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                Toast.makeText(context, "Logged out!", Toast.LENGTH_SHORT).show()
            }

//        }
        return root


    }
}