package ie.wit.madassignment.ui.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ie.wit.madassignment.LoginActivity
import ie.wit.madassignment.MainActivity
import ie.wit.madassignment.R
import ie.wit.madassignment.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactViewModel =
            ViewModelProviders.of(this).get(ContactViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        val textView: TextView = root.findViewById(R.id.hint)
        val button = root.findViewById<Button>(R.id.sendEmailBtn);

        button.setOnClickListener {
            val recipient = recipient.text.toString().trim()
            val subject = subject.text.toString().trim()
            val message = message.text.toString().trim()


            Toast.makeText(context, "Email intent launched!", Toast.LENGTH_SHORT).show()
//            sendEmail(recipient, subject, message)
        }




        contactViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
//        setContentView(R.layout.fragment_contact)
//        sendEmailBtn.setOnClickListener {
//            var recipient = recipient.text.toString().trim()
//            var subject = subject.text.toString().trim()
//            var message = message.text.toString().trim()
//
//            sendEmail(recipient, subject, message)
//        }
//    }

//
//      public fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == R.id.back) {
//            startActivity((Intent(this, MainActivity)))
//        }
//        return super.onOptionsItemSelected(item)
//    }

//
//}