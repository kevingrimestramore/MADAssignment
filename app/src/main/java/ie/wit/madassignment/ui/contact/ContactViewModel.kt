package ie.wit.madassignment.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lounge is a utility app which gathers key everyday features from other applications and widgets in one place, without the excess. It's your one stop for alarms, on the go note-taking and weather checking."
    }
    val text: LiveData<String> = _text
}