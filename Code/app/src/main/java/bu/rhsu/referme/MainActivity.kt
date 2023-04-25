package bu.rhsu.referme

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import bu.rhsu.referme.databinding.ActivityMainBinding
import bu.rhsu.referme.fragments.SingleReviewFragment
import bu.rhsu.referme.ui.login.LoginActivity
import bu.rhsu.referme.ui.login.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import bu.rhsu.referme.fragments.ResultsFragment
import bu.rhsu.referme.datalayer.Provider

class MainActivity : AppCompatActivity(), ResultsFragment.OnProviderClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)


        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

/*        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.logout -> {
/*                val logoutIntent =
                    Intent(this, LoginActivity::class.java).apply{
                        putExtra("Logout", true)
                    }

                startActivity(logoutIntent)*/
                loginViewModel.logout()
                val loginIntent = Intent(this, LoginActivity::class.java)

                startActivity(loginIntent)
                finish()

                Toast.makeText(
                    applicationContext,
                    "Bye!",
                    Toast.LENGTH_LONG
                ).show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onProviderClick(provider: Provider) {
        //findViewById<SlidingPaneLayout>(R.id.slidepane).open()
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(R.id.action_resultsFragment_to_reviewFragment)
    }

}