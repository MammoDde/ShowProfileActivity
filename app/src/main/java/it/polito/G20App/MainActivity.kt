package it.polito.G20App

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import it.polito.G20App.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

private var currentPhotoPath: String? = null

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var photo: Photo = Photo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView


        //FASE DI INIZIALIZZAZIONE DEL HEADER DEL MENU
        //Caricamento shared preferences
        val sharedPrefR = this.getPreferences(Context.MODE_PRIVATE)
        val profileInfo = "{'full name' : '${getString(R.string.full_name)}', nickname : '${getString(R.string.nickname)}', " +
                "email : '${getString(R.string.email)}', location : '${getString(R.string.location)}', skill1 : '${getString(R.string.skill1)}'," +
                " skill2 : '${getString(R.string.skill2)}', description1 : '${getString(R.string.description1)}', " +
                "description2 : '${getString(R.string.description2)}', " + "path: '${currentPhotoPath.toString()}'}"
        val json = sharedPrefR?.getString("profile", profileInfo)?.let { JSONObject(it) }

        //SETTING IMAGE PROFILE
        val headerView: View = binding.navView.getHeaderView(0)
        val img: ImageView = headerView.findViewById(R.id.nav_head_avatar)
        try {
            if (json != null) {
                if(json.get("path").toString() == "null")
                    currentPhotoPath = null
                else
                    currentPhotoPath = json.get("path").toString()
            }
        } catch (e : JSONException) {
            println(e)
        }
        if(currentPhotoPath != null){
            val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        }

        //SETTING NAMES
        val tv1: TextView = headerView.findViewById(R.id.nav_head_username)
        if (json != null) {
            tv1.text = (json.get("full name").toString())
        }


        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_adv_list
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setCheckedItem(R.id.nav_adv_list)

        //questo metodo invece ci serve per abilitare un listener quando clicchiamo il menu laterale
        navView.setNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val drawerLayout: DrawerLayout = binding.drawerLayout

        if(item.itemId == R.id.nav_adv_list){
            //This if is only a reminder that this is the home of app
        }
        if(item.itemId == R.id.nav_show_profile){
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_adv_list_to_nav_show_profile)
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)  || super.onSupportNavigateUp()

    }
}

