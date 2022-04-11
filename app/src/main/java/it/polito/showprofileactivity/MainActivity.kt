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

class MainActivity : AppCompatActivity() {

    var photo: Photo = Photo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img: ImageView = findViewById(R.id.imageView)

        if(currentPhotoPath != null){
            val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        }

        val sharedPrefR = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val profileInfo = "{'full name' : '${getString(R.string.full_name)}', nickname : '${getString(R.string.nickname)}', " +
                "email : '${getString(R.string.email)}', location : '${getString(R.string.location)}', skill1 : '${getString(R.string.skill1)}'," +
                " skill2 : '${getString(R.string.skill2)}', description1 : '${getString(R.string.description1)}', " +
                "description2 : '${getString(R.string.description2)}'}"

        val json = JSONObject(sharedPrefR.getString("profile", profileInfo))

        var tv1: TextView = findViewById(R.id.fullname)
        var tv2: TextView = findViewById(R.id.nickname)
        var tv3: TextView = findViewById(R.id.email)
        var tv4: TextView = findViewById(R.id.location)
        var tv5: TextView = findViewById(R.id.skill1)
        var tv6: TextView = findViewById(R.id.skill2)
        var tv7: TextView = findViewById(R.id.description1)
        var tv8: TextView = findViewById(R.id.description2)

        tv1.setText(json.get("full name").toString())
        tv2.setText(json.get("nickname").toString())
        tv3.setText(json.get("email").toString())
        tv4.setText(json.get("location").toString())
        tv5.setText(json.get("skill1").toString())
        tv6.setText(json.get("skill2").toString())
        tv7.setText(json.get("description1").toString())
        tv8.setText(json.get("description2").toString())
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


            val profileInfo = "{'full name' : '$str1', nickname : '$str2', " +
                    "email : '$str3', location : '$str4', skill1 : '$str5'," +
                    " skill2 : '$str6', description1 : '$str7', " +
                    "description2 : '$str8'}"
            val json = JSONObject(profileInfo)

            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString("profile", json.toString())
                apply()
            }

            currentPhotoPath = data.getStringExtra("path")
            if(currentPhotoPath != null) {
                var bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
                img.setImageBitmap(bitmap)
            }

            tv1.setText(str1)
            tv2.setText(str2)
            tv3.setText(str3)
            tv4.setText(str4)
            tv5.setText(str5)
            tv6.setText(str6)
            tv7.setText(str7)
            tv8.setText(str8)
        } else {
            tv1.setText("error")
            tv2.setText("error")
            tv3.setText("error")
            tv4.setText("error")
            tv5.setText("error")
            tv6.setText("error")
            tv7.setText("error")
            tv8.setText("error")
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}

