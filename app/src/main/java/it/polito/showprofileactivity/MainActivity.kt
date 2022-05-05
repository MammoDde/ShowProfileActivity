package it.polito.showprofileactivity

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import it.polito.showprofileactivity.databinding.ActivityMainBinding


import com.google.android.material.snackbar.Snackbar
import org.json.JSONException
import org.json.JSONObject

private var currentPhotoPath: String? = null

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var photo: Photo = Photo()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

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
                R.id.nav_home, R.id.nav_edit_profile, R.id.nav_adv_list, R.id.nav_check
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

/*private fun createItems(n: Int): MutableList<Item> {
    val l = mutableListOf<Item>()
    for (i in 1..n) {
        val i = Item(i,"name$i", "role$i")
        l.add(i)
    }
    return l
}
override fun onCreate(savedInsnceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activitymain)
    val rv = findViewById<RecyclerView>(R.id.)
    rv.layoutManager = LinearLayoutManager(this)
    val adapter = ItemAdapter(createItems(2000))
    rv.adapter = adapter
    val s = findViewById<Switch>(R.id.switch1)
    s.setOnCheckedChangeListener { , b ->
        adapter.addFilter(b)
    }
}*/

//menu con i tre puntini
/* override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.navdrawer_menu, menu)
    return true
}*/

override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_main)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}



}

