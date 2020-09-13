package ie.wit.madassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class SplashActivity : AppCompatActivity() {

    // *** Splash activity not recognised in Android Manifest despite troubleshooting efforts
//    layout file and timer present
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}