package ie.wit.madassignment.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to Lounge, a one stop for all the key features of your most used utility apps. Sign in via Google to save your notes and location settings."
    }
    val text: LiveData<String> = _text
}