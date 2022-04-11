package it.polito.showprofileactivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import java.io.ByteArrayOutputStream

private var currentPhotoPath: String? = null

class MainActivity : AppCompatActivity() {

    var photo: Photo = Photo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var img: ImageView = findViewById(R.id.imageView)

        if(currentPhotoPath != null){
            var bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
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
        var img: ImageView = findViewById(R.id.imageView)
        var tv1: TextView = findViewById(R.id.fullname)
        var tv2: TextView = findViewById(R.id.nickname)
        var tv3: TextView = findViewById(R.id.email)
        var tv4: TextView = findViewById(R.id.location)
        var tv5: TextView = findViewById(R.id.skill1)
        var tv6: TextView = findViewById(R.id.skill2)
        var tv7: TextView = findViewById(R.id.description1)
        var tv8: TextView = findViewById(R.id.description2)

        if(currentPhotoPath != null){
            var bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        } else {
            var b: Bitmap = Bitmap.createBitmap(img.drawToBitmap())
            var bt = ByteArrayOutputStream()
            b.compress(Bitmap.CompressFormat.PNG,100,bt)
            i.putExtra("image",bt.toByteArray())
        }

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
        var img: ImageView = findViewById(R.id.imageView)
        var tv1: TextView = findViewById(R.id.fullname)
        var tv2: TextView = findViewById(R.id.nickname)
        var tv3: TextView = findViewById(R.id.email)
        var tv4: TextView = findViewById(R.id.location)
        var tv5: TextView = findViewById(R.id.skill1)
        var tv6: TextView = findViewById(R.id.skill2)
        var tv7: TextView = findViewById(R.id.description1)
        var tv8: TextView = findViewById(R.id.description2)

        if (data != null) {
            var str1: String? = data.getStringExtra("Name")
            var str2: String? = data.getStringExtra("Nickname")
            var str3: String? = data.getStringExtra("email")
            var str4: String? = data.getStringExtra("location")
            var str5: String? = data.getStringExtra("skill1")
            var str6: String? = data.getStringExtra("skill2")
            var str7: String? = data.getStringExtra("description1")
            var str8: String? = data.getStringExtra("description2")

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

