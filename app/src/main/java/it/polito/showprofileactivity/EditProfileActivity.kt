package it.polito.showprofileactivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.drawToBitmap
import java.io.ByteArrayOutputStream

private const val REQUEST_IMAGE_CAPTURE = 1
private const val REQUEST_ACTION_PICK = 2

private const val name = "icon"
private var currentPhotoPath: String = "empty"

class EditProfileActivity : AppCompatActivity() {

    var photo: Photo = Photo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var editButton: ImageButton = findViewById(R.id.imageButton2)
        var img: ImageView = findViewById(R.id.imageView)
        var name: String? = intent.getStringExtra("Name")
        var nick: String? = intent.getStringExtra("Nickname")
        var email: String? = intent.getStringExtra("email")
        var loc: String? = intent.getStringExtra("location")
        var skill1: String? = intent.getStringExtra("skill1")
        var skill2: String? = intent.getStringExtra("skill2")
        var desc1: String? = intent.getStringExtra("description1")
        var desc2: String? = intent.getStringExtra("description2")
        var ed1: EditText = findViewById(R.id.edit_fullname)
        var ed2: EditText = findViewById(R.id.edit_nickname)
        var ed3: EditText = findViewById(R.id.edit_email)
        var ed4: EditText = findViewById(R.id.edit_location)
        var ed5: EditText = findViewById(R.id.edit_skill1)
        var ed6: EditText = findViewById(R.id.edit_skill2)
        var ed7: EditText = findViewById(R.id.edit_description1)
        var ed8: EditText = findViewById(R.id.edit_description2)

        if (intent.getStringExtra("path") != null && intent.getStringExtra("path") != "empty"){
            currentPhotoPath = intent.getStringExtra("path").toString()
            var bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        } else {
            var bitmap: Bitmap = BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"),0,intent.getByteArrayExtra("image")!!.size)
            img.setImageBitmap(bitmap)
        }

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
                        true
                    }
                    R.id.menu_open_gallery -> {
                        val galleryIntent = Intent(Intent.ACTION_PICK)
                        galleryIntent.type = "image/*"
                        startActivityForResult(galleryIntent, REQUEST_ACTION_PICK);
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
                val imageBitmap = data?.extras?.get("data") as Bitmap

                // set bitmap of edit view
                image.setImageBitmap(imageBitmap)

                // save image to Internal Storage
                currentPhotoPath = photo.saveToInternalStorage(imageBitmap, this, "imageDir", name)
            }else {
                val i = Intent(this,EditCheck::class.java)
                startActivity(i)
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
        if(requestCode == REQUEST_ACTION_PICK && resultCode == RESULT_OK){
            image.setImageURI(data?.data)
        }
    }

    @SuppressLint("WrongThread")
    override fun onBackPressed() {
        val i = Intent()

        var img: ImageView = findViewById<ImageView>(R.id.imageView)
        var ed1 = findViewById<EditText>(R.id.edit_fullname)
        var ed2: EditText = findViewById(R.id.edit_nickname)
        var ed3: EditText = findViewById(R.id.edit_email)
        var ed4: EditText = findViewById(R.id.edit_location)
        var ed5: EditText = findViewById(R.id.edit_skill1)
        var ed6: EditText = findViewById(R.id.edit_skill2)
        var ed7: EditText = findViewById(R.id.edit_description1)
        var ed8: EditText = findViewById(R.id.edit_description2)



        i.putExtra("Name", ed1.text.toString())
        i.putExtra("Nickname",ed2.text.toString())
        i.putExtra("email",ed3.text.toString())
        i.putExtra("location",ed4.text.toString())
        i.putExtra("skill1",ed5.text.toString())
        i.putExtra("skill2",ed6.text.toString())
        i.putExtra("description1",ed7.text.toString())
        i.putExtra("description2",ed8.text.toString())

        if(currentPhotoPath != "empty"){
            i.putExtra("path", currentPhotoPath)
        }else{
            var bitmap: Bitmap = Bitmap.createBitmap(img.drawToBitmap())
            var bt = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,50,bt)
            i.putExtra("image",bt.toByteArray())
        }

        setResult(Activity.RESULT_OK,i)

        super.onBackPressed()

    }




}