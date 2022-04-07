package it.polito.showprofileactivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

private var currentPhotoPath: String = "empty"

class MainActivity : AppCompatActivity() {
    var newProfile: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var img: ImageView = findViewById<ImageView>(R.id.imageView)

        if(currentPhotoPath != "empty"){
            var bitmap: Bitmap? = loadImageFromStorage(currentPhotoPath, "icon.jpg")
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

    //Retrieve Image
    private fun loadImageFromStorage(path: String?, imageName : String) : Bitmap? {

        //Try to get the file from the bitmap
        try {

            //Get the file and load the bitmap
            val f = File(path, imageName);
            val b = BitmapFactory.decodeStream(FileInputStream(f))

            return b;

            /*
            val img: ImageView = findViewById(R.id.imgPicker) as ImageView
            img.setImageBitmap(b)
             */
        }
        catch (e: FileNotFoundException) {
            //e.printStackTrace()
            Log.i("error", "file not found!")
        }

        return null;
    }
    private fun editProfile(){
        val i = Intent(this,EditProfileActivity::class.java)
        var img: ImageView = findViewById<ImageView>(R.id.imageView)
        var tv1: TextView = findViewById(R.id.fullname)
        var tv2: TextView = findViewById(R.id.nickname)
        var tv3: TextView = findViewById(R.id.email)
        var tv4: TextView = findViewById(R.id.location)
        var tv5: TextView = findViewById(R.id.skill1)
        var tv6: TextView = findViewById(R.id.skill2)
        var tv7: TextView = findViewById(R.id.description1)
        var tv8: TextView = findViewById(R.id.description2)
        var bitmap: Bitmap = Bitmap.createBitmap(img.drawToBitmap())
        var bt: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,50,bt)
        i.putExtra("image",bt.toByteArray())
        i.putExtra("Name",tv1.text)
        i.putExtra("Nickname",tv2.text)
        i.putExtra("email",tv3.text)
        i.putExtra("location",tv4.text)
        i.putExtra("skill1",tv5.text)
        i.putExtra("skill2",tv6.text)
        i.putExtra("description1",tv7.text)
        i.putExtra("description2",tv8.text)
        //launcher.launch(i)
        startActivityForResult(i,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var img: ImageView = findViewById<ImageView>(R.id.imageView)
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

            currentPhotoPath = data.getStringExtra("path").toString()
            var bitmap: Bitmap? = loadImageFromStorage(currentPhotoPath, "icon.jpg")
            //var bitmap: Bitmap = BitmapFactory.decodeByteArray(data.getByteArrayExtra("image"),0,data.getByteArrayExtra("image")!!.size)
            img.setImageBitmap(bitmap)
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Read values from the "savedInstanceState"-object and put them in your textview
    }

      override fun onSaveInstanceState(outState: Bundle) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState)
    }
}

