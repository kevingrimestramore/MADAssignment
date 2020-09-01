package ie.wit.madassignment.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Weather Fragment"
    }
    val text: LiveData<String> = _text
}