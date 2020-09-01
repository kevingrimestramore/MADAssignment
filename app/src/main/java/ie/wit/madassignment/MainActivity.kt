package ie.wit.madassignment

import android.content.Intent
import android.os.Bundle
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
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.data.model.Resource
import com.google.firebase.auth.FirebaseAuth
import ie.wit.madassignment.databinding.ActivityMainBinding
import ie.wit.madassignment.ui.contact.ContactFragment
import kotlinx.android.synthetic.main.nav_header_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val auth = FirebaseAuth.getInstance().currentUser
    private lateinit var appBarConfiguration: AppBarConfiguration
    var userTitle = R.string.nav_header_title
    var userEmail = R.string.nav_header_subtitle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (auth == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            userTitle = auth.displayName
            userEmail = auth.email
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Feedback/Troubleshooting?", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener {
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                startActivity((Intent(this, LoginActivity)))
                Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
            }
        }

//          Access contact form from nav drawer
//        navView.menu.findItem(R.id.nav_contact).setOnMenuItemClickListener {
//
//        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dashboard,
                R.id.nav_alarms,
                R.id.nav_notes,
                R.id.nav_weather,
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
}