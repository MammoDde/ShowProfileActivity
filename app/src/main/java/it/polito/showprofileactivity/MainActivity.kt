package it.polito.showprofileactivity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

private var currentPhotoPath: String? = null

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var photo: Photo = Photo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img: ImageView = findViewById(R.id.imageView)



        val sharedPrefR = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val profileInfo = "{'full name' : '${getString(R.string.full_name)}', nickname : '${getString(R.string.nickname)}', " +
                "email : '${getString(R.string.email)}', location : '${getString(R.string.location)}', skill1 : '${getString(R.string.skill1)}'," +
                " skill2 : '${getString(R.string.skill2)}', description1 : '${getString(R.string.description1)}', " +
                "description2 : '${getString(R.string.description2)}', " + "path: '${currentPhotoPath}'}"

        val json = sharedPrefR.getString("profile", profileInfo)?.let { JSONObject(it) }

        val tv1: TextView = findViewById(R.id.fullname)
        val tv2: TextView = findViewById(R.id.nickname)
        val tv3: TextView = findViewById(R.id.email)
        val tv4: TextView = findViewById(R.id.location)
        val tv5: TextView = findViewById(R.id.skill1)
        val tv6: TextView = findViewById(R.id.skill2)
        val tv7: TextView = findViewById(R.id.description1)
        val tv8: TextView = findViewById(R.id.description2)

        if(json != null){
            tv1.text = json.get("full name").toString()
            tv2.text = (json.get("nickname").toString())
            tv3.text = (json.get("email").toString())
            tv4.text = (json.get("location").toString())
            tv5.text = (json.get("skill1").toString())
            tv6.text = (json.get("skill2").toString())
            tv7.text = (json.get("description1").toString())
            tv8.text = (json.get("description2").toString())
            currentPhotoPath = json.get("path").toString()
        }

        if(currentPhotoPath != null){
            val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        }

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
                editProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun editProfile(){
        val i = Intent(this,EditProfileActivity::class.java)
        val tv1: TextView = findViewById(R.id.fullname)
        val tv2: TextView = findViewById(R.id.nickname)
        val tv3: TextView = findViewById(R.id.email)
        val tv4: TextView = findViewById(R.id.location)
        val tv5: TextView = findViewById(R.id.skill1)
        val tv6: TextView = findViewById(R.id.skill2)
        val tv7: TextView = findViewById(R.id.description1)
        val tv8: TextView = findViewById(R.id.description2)

        i.putExtra("path", currentPhotoPath)

        i.putExtra("Name",tv1.text)
        i.putExtra("Nickname",tv2.text)
        i.putExtra("email",tv3.text)
        i.putExtra("location",tv4.text)
        i.putExtra("skill1",tv5.text)
        i.putExtra("skill2",tv6.text)
        i.putExtra("description1",tv7.text)
        i.putExtra("description2",tv8.text)
        startActivityForResult(i,123)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val img: ImageView = findViewById(R.id.imageView)
        val tv1: TextView = findViewById(R.id.fullname)
        val tv2: TextView = findViewById(R.id.nickname)
        val tv3: TextView = findViewById(R.id.email)
        val tv4: TextView = findViewById(R.id.location)
        val tv5: TextView = findViewById(R.id.skill1)
        val tv6: TextView = findViewById(R.id.skill2)
        val tv7: TextView = findViewById(R.id.description1)
        val tv8: TextView = findViewById(R.id.description2)

        if (data != null) {
            val str1: String? = data.getStringExtra("Name")
            val str2: String? = data.getStringExtra("Nickname")
            val str3: String? = data.getStringExtra("email")
            val str4: String? = data.getStringExtra("location")
            val str5: String? = data.getStringExtra("skill1")
            val str6: String? = data.getStringExtra("skill2")
            val str7: String? = data.getStringExtra("description1")
            val str8: String? = data.getStringExtra("description2")

            currentPhotoPath = data.getStringExtra("path")
            if(currentPhotoPath != null) {
                val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
                img.setImageBitmap(bitmap)
            }

            val profileInfo = "{'full name' : '$str1', nickname : '$str2', " +
                    "email : '$str3', location : '$str4', skill1 : '$str5'," +
                    " skill2 : '$str6', description1 : '$str7', " +
                    "description2 : '$str8', " + "path: '${currentPhotoPath}'}"
            val json = JSONObject(profileInfo)

            val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("profile", json.toString())
                apply()
            }

            tv1.text = str1
            tv2.text = str2
            tv3.text = str3
            tv4.text = str4
            tv5.text = str5
            tv6.text = str6
            tv7.text = str7
            tv8.text = str8
        } else {
            tv1.text = getString(R.string.error)
            tv2.text = getString(R.string.error)
            tv3.text = getString(R.string.error)
            tv4.text = getString(R.string.error)
            tv5.text = getString(R.string.error)
            tv6.text = getString(R.string.error)
            tv7.text = getString(R.string.error)
            tv8.text = getString(R.string.error)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}

