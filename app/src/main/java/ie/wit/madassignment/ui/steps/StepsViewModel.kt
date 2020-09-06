package ie.wit.madassignment.ui.steps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StepsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Track your steps throughout the day using the refresh button below. Count resets at midnight."
    }


    val text: LiveData<String> = _text
}