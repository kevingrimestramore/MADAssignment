package ie.wit.madassignment.ui.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import ie.wit.madassignment.LoginActivity
import ie.wit.madassignment.MainActivity
import ie.wit.madassignment.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_contact)
        sendEmailBtn.setOnClickListener {
            var recipient = recipient.text.toString().trim()
            var subject = subject.text.toString().trim()
            var message = message.text.toString().trim()

            sendEmail(recipient, subject, message)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.back) {
            startActivity((Intent(this, MainActivity)))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client"))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}