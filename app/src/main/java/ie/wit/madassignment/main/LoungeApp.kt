package ie.wit.madassignment.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth


class LoungeApp : Application() {



    // [START declare_auth]
    lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate() {
        super.onCreate()
    }
}