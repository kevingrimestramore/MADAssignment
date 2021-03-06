package ie.wit.madassignment.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Found an issue? Contact the developer below."
    }
    val text: LiveData<String> = _text
}