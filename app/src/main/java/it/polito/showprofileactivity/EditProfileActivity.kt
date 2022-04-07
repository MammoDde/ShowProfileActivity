package it.polito.showprofileactivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.drawToBitmap
import com.android.volley.toolbox.ImageLoader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val REQUEST_IMAGE_CAPTURE = 1
private const val name = "icon.jpg"
private lateinit var photoFile: File
private lateinit var currentPhotoPath: String

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var editButton: ImageButton = findViewById(R.id.imageButton2)
        var img: ImageView = findViewById<ImageView>(R.id.imageView)
        var name: String? = intent.getStringExtra("Name")
        var nick: String? = intent.getStringExtra("Nickname")
        var email: String? = intent.getStringExtra("email")
        var loc: String? = intent.getStringExtra("location")
        var skill1: String? = intent.getStringExtra("skill1")
        var skill2: String? = intent.getStringExtra("skill2")
        var desc1: String? = intent.getStringExtra("description1")
        var desc2: String? = intent.getStringExtra("description2")
        var ed1: EditText = findViewById<EditText>(R.id.edit_fullname)
        var ed2: EditText = findViewById<EditText>(R.id.edit_nickname)
        var ed3: EditText = findViewById<EditText>(R.id.edit_email)
        var ed4: EditText = findViewById<EditText>(R.id.edit_location)
        var ed5: EditText = findViewById<EditText>(R.id.edit_skill1)
        var ed6: EditText = findViewById<EditText>(R.id.edit_skill2)
        var ed7: EditText = findViewById<EditText>(R.id.edit_description1)
        var ed8: EditText = findViewById<EditText>(R.id.edit_description2)

        var bitmap: Bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"),0,intent.getByteArrayExtra("image")!!.size)
        img.setImageBitmap(bitmap)
        ed1.setText(name)
        ed2.setText(nick)
        ed3.setText(email)
        ed4.setText(loc)
        ed5.setText(skill1)
        ed6.setText(skill2)
        ed7.setText(desc1)
        ed8.setText(desc2)


        editButton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.menu_open_camera -> {
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        try {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                        }catch (e : ActivityNotFoundException){
                            println(e)
                        }
                                // Continue only if the File was successfully created

                        true
                    }
                    R.id.menu_open_gallery -> {
                        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
                        galleryIntent.type = "image/*"
                        startActivity(galleryIntent);
                        true
                    }
                    else -> false
                }

            }

            popupMenu.inflate(R.menu.photo_menu)
            popupMenu.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image = findViewById<ImageView>(R.id.imageView)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if(data != null) {
                var ed1 = findViewById<EditText>(R.id.edit_fullname)
                val imageBitmap = data?.extras?.get("data") as Bitmap
                image.setImageBitmap(imageBitmap)

                currentPhotoPath =  saveToInternalStorage(imageBitmap, "imageDir", name)
            }else {
                val i = Intent(this,EditCheck::class.java)
                startActivity(i)
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun saveToInternalStorage(bitmapImage : Bitmap, dir : String, imageName : String ): String {

        // path to /data/data/yourapp/app_data/imageDir
        val directory : File = getDir(dir, Context.MODE_PRIVATE)

        // Create imageDir
        val mypath : File = File(directory, imageName)
        var fos : FileOutputStream? = null

        try {
            fos = FileOutputStream(mypath)

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)

        }
        catch (e: Exception) {
            //e.printStackTrace()

            //Check for it
            Log.i("error", "image can't be saved!")
        }
        finally {

            //Close the stream
            try {
                fos?.close()
            }
            catch (e: IOException) {

                //e.printStackTrace()
                Log.i("error", "stream can't be closed!")
            }
        }

        //Return the path
        return directory.getAbsolutePath()
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


    @SuppressLint("WrongThread")
    override fun onBackPressed() {
        val i = Intent()
        var img: ImageView = findViewById<ImageView>(R.id.imageView)
        var ed1 = findViewById<EditText>(R.id.edit_fullname)
        var ed2: EditText = findViewById<EditText>(R.id.edit_nickname)
        var ed3: EditText = findViewById<EditText>(R.id.edit_email)
        var ed4: EditText = findViewById<EditText>(R.id.edit_location)
        var ed5: EditText = findViewById<EditText>(R.id.edit_skill1)
        var ed6: EditText = findViewById<EditText>(R.id.edit_skill2)
        var ed7: EditText = findViewById<EditText>(R.id.edit_description1)
        var ed8: EditText = findViewById<EditText>(R.id.edit_description2)

        var bitmap: Bitmap = Bitmap.createBitmap(img.drawToBitmap())
        var bt: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,50,bt)
        i.putExtra("image",bt.toByteArray())

        i.putExtra("Name", ed1.text.toString())
        i.putExtra("Nickname",ed2.text.toString())
        i.putExtra("email",ed3.text.toString())
        i.putExtra("location",ed4.text.toString())
        i.putExtra("skill1",ed5.text.toString())
        i.putExtra("skill2",ed6.text.toString())
        i.putExtra("description1",ed7.text.toString())
        i.putExtra("description2",ed8.text.toString())

        i.putExtra("path", currentPhotoPath)
        setResult(Activity.RESULT_OK,i)

        super.onBackPressed()

    }




}