package ie.wit.madassignment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.Resource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.firebase.auth.FirebaseAuth
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import ie.wit.madassignment.databinding.ActivityMainBinding
import ie.wit.madassignment.ui.contact.ContactFragment
import kotlinx.android.synthetic.main.fragment_steps.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.w3c.dom.Text

const val TAG = "StepCounter"

enum class FitActionRequestCode {
    SUBSCRIBE,
    READ_DATA
}

class MainActivity : AppCompatActivity() {

//    *** Google fit data
//    private val fitnessOptions = FitnessOptions.builder()
//        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//        .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
//        .build()
//
//    private val runningQOrLater =
//        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    lateinit var binding: ActivityMainBinding
    val auth = FirebaseAuth.getInstance().currentUser
    private lateinit var appBarConfiguration: AppBarConfiguration
    var userTitle = R.string.nav_header_title
    var userEmail = R.string.nav_header_subtitle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//      *** Logic to launch email intent via contact fragment, uses data from edit text boxes
        fun sendEmail(recipient: String, subject: String, message: String) {
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


//      *** Google Fit call
//        checkPermissionsAndRun(FitActionRequestCode.SUBSCRIBE)

//        *** Check auth status to update name and email on dashboard and nav header.

//        if (auth == null) {
//            startActivity(Intent(this, LoginActivity::class.java))
//        } else {
//            userTitle = auth.displayName
//            userEmail = auth.email
//        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

//        *** Action bar intended to connect to contact fragment
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Feedback/Troubleshooting?", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

//        *** Use logout button in nav drawer to logout of AuthUI, toast for visual feedback
//        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener {
//            AuthUI.getInstance().signOut(this).addOnSuccessListener {
//                startActivity((Intent(this, LoginActivity)))
//                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
//            }
//        }


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dashboard,
                R.id.nav_alarms,
                R.id.nav_notes,
                R.id.nav_weather,
                R.id.nav_steps,
                R.id.nav_contact
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_contact) {
            val intent = Intent(this, ContactFragment::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    *** Google Fit authorization + readData(), called in the steps fragment to update the progress bar
//
//    private fun fitSignIn(requestCode: FitActionRequestCode) {
//        if (oAuthPermissionsApproved()) {
//            performActionForRequestCode(requestCode)
//        } else {
//            requestCode.let {
//                GoogleSignIn.requestPermissions(
//                    this,
//                    requestCode.ordinal,
//                    getGoogleAccount(), fitnessOptions)
//            }
//        }
//    }
//
//    private fun checkPermissionsAndRun(fitActionRequestCode: FitActionRequestCode) {
//        if (permissionApproved()) {
//            fitSignIn(fitActionRequestCode)
//        } else {
//            requestRuntimePermissions(fitActionRequestCode)
//        }
//    }
//
//    private fun fitSignIn(requestCode: FitActionRequestCode) {
//        if (oAuthPermissionsApproved()) {
//            performActionForRequestCode(requestCode)
//        } else {
//            requestCode.let {
//                GoogleSignIn.requestPermissions(
//                    this,
//                    requestCode.ordinal,
//                    getGoogleAccount(), fitnessOptions)
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (resultCode) {
//            RESULT_OK -> {
//                val postSignInAction = FitActionRequestCode.values()[requestCode]
//                postSignInAction.let {
//                    performActionForRequestCode(postSignInAction)
//                }
//            }
//            else -> oAuthErrorMsg(requestCode, resultCode)
//        }
//    }
//
//    private fun performActionForRequestCode(requestCode: FitActionRequestCode) = when (requestCode) {
//        FitActionRequestCode.READ_DATA -> readData()
//        FitActionRequestCode.SUBSCRIBE -> subscribe()
//    }
//
//    private fun oAuthErrorMsg(requestCode: Int, resultCode: Int) {
//        val message = """
//            There was an error signing into Fit. Check the troubleshooting section of the README
//            for potential issues.
//            Request code was: $requestCode
//            Result code was: $resultCode
//        """.trimIndent()
//    }
//
//    private fun oAuthPermissionsApproved() = GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions)
//
//    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(this, fitnessOptions)
//
//    private fun subscribe() {
//        Fitness.getRecordingClient(this, getGoogleAccount())
//            .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(applicationContext, "Successfully subscribed!", Toast.LENGTH_SHORT)
//                } else {
//                    Toast.makeText(applicationContext, "There was a problem subscribing.", Toast.LENGTH_SHORT)
//                }
//            }
//    }
//
//    private fun readData() {
//        Fitness.getHistoryClient(this, getGoogleAccount())
//            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
//            .addOnSuccessListener { dataSet ->
//                val total = when {
//                    dataSet.isEmpty -> 0
//                    else -> dataSet.dataPoints.first().getValue(Field.FIELD_STEPS).asInt()
//                }
//                step_total_text_view2.setText("$total")
//
//
//                val goal = 7000
//                val circularProgressBar = findViewById<CircularProgressBar>(R.id.progressBar)
//                circularProgressBar.apply {
//                    setProgressWithAnimation(total.toFloat(), 1000)
//                    progressMax = goal.toFloat()
//                }
//
//                if (total <= goal) {
//                    val toast = Toast.makeText(applicationContext, "Keep moving to hit goal!", Toast.LENGTH_SHORT)
//                    toast.show()
//                } else if (total > goal) {
//                    val toast = Toast.makeText(applicationContext, "Congrats! Step goal achieved!", Toast.LENGTH_SHORT)
//                    toast.show()
//                }
//            }
//
//    }
//
//    private fun permissionApproved(): Boolean {
//        val approved = if (runningQOrLater) {
//            PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACTIVITY_RECOGNITION)
//        } else {
//            true
//        }
//        return approved
//    }
//
//    private fun requestRuntimePermissions(requestCode: FitActionRequestCode) {
//        val shouldProvideRationale =
//            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACTIVITY_RECOGNITION)
//
//         requestCode.let {
//            if (shouldProvideRationale) {
//                Snackbar.make(
//                    findViewById(R.id.main_activity_view),
//                    R.string.permission_rationale,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.ok) {
//                        ActivityCompat.requestPermissions(this,
//                            arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
//                            requestCode.ordinal)
//                    }
//                    .show()
//            } else {
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
//                    requestCode.ordinal)
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
//                                            grantResults: IntArray) {
//        when {
//            grantResults.isEmpty() -> {
//                val toast = Toast.makeText(applicationContext, "User interaction was cancelled.", Toast.LENGTH_SHORT)
//                toast.show()
//            }
//            grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
//                // Permission was granted.
//                val fitActionRequestCode = FitActionRequestCode.values()[requestCode]
//                fitActionRequestCode.let {
//                    fitSignIn(fitActionRequestCode)
//                }
//            }
//            else -> {
//            }
//        }
//    }
}