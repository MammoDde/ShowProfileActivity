package it.polito.showprofileactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.icon -> {
                val i = Intent(this,EditProfileActivity::class.java)
                i.putExtra("Name",getString(R.string.mario_rossi))
                i.putExtra("Nickname",getString(R.string.mario22))
                i.putExtra("email",getString(R.string.mario_rossi_polito_it))
                i.putExtra("location",getString(R.string.corso_duca_degli_abruzzi_torino))
                i.putExtra("skill1",getString(R.string.english))
                i.putExtra("skill2",getString(R.string.babysitting))
                i.putExtra("description1",getString(R.string.a_good_english_teacher))
                i.putExtra("description2",getString(R.string.i_take_care_of_baby))
                startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}