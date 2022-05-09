package it.polito.showprofileactivity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import it.polito.showprofileactivity.databinding.ActivityMainBinding

private var currentPhotoPath: String? = null

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var photo: Photo = Photo()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    val vm by viewModels<TimeSlotVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setCheckedItem(R.id.nav_home)

        // quando si clicca nel menu laterale questo cambia subito fragment
        //navView.setupWithNavController(navController)

        //questo metodo invece ci serve per abilitare un listener quando clicchiamo il menu laterale
        navView.setNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val drawerLayout: DrawerLayout = binding.drawerLayout

        if(item.itemId == R.id.nav_edit_profile){
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_home_to_editProfileActivity)
            supportActionBar?.title = "Edit profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        if(item.itemId == R.id.nav_adv_list){
            supportActionBar?.title = "My time slots"
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_home_to_advListFragment)
        }
        if(item.itemId == R.id.nav_check){
            val bundle = Bundle()
            val string = "Funziona"
            bundle.putString("id", string)
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_home_to_nav_check, bundle)
            supportActionBar?.title = "Check"
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)  || super.onSupportNavigateUp()

    }
}

