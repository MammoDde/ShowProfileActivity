package it.polito.showprofileactivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val REQUEST_IMAGE_CAPTURE = 1
private const val name = "photoG20.jpg"
private lateinit var photoFile: File
private lateinit var currentPhotoPath: String

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var editButton: ImageButton = findViewById(R.id.imageButton2)

        var name: String? = intent.getStringExtra("Name")
        var nick: String? = intent.getStringExtra("Nickname")
        var email: String? = intent.getStringExtra("email")
        var loc: String? = intent.getStringExtra("location")
        var skill1: String? = intent.getStringExtra("skill1")
        var skill2: String? = intent.getStringExtra("skill2")
        var desc1: String? = intent.getStringExtra("description1")
        var desc2: String? = intent.getStringExtra("description2")
        var ed1: EditText = findViewById<EditText>(R.id.editTextTextPersonName8)
        var ed2: EditText = findViewById<EditText>(R.id.editTextTextPersonName9)
        var ed3: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        var ed4: EditText = findViewById<EditText>(R.id.editTextTextPersonName10)
        var ed5: EditText = findViewById<EditText>(R.id.editTextTextPersonName11)
        var ed6: EditText = findViewById<EditText>(R.id.editTextTextPersonName16)
        var ed7: EditText = findViewById<EditText>(R.id.editTextTextMultiLine)
        var ed8: EditText = findViewById<EditText>(R.id.editTextTextMultiLine2)

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
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                            // Ensure that there's a camera activity to handle the intent
                            takePictureIntent.resolveActivity(packageManager)?.also {
                                // Create the File where the photo should go
                                val photoFile: File? = try {
                                    createImageFile()
                                } catch (ex: IOException) {
                                    // Error occurred while creating the File
                                    null
                                }
                                // Continue only if the File was successfully created
                                photoFile?.also {
                                    val photoURI: Uri = FileProvider.getUriForFile(
                                        this,
                                        "com.example.android.fileprovider",
                                        it
                                    )
                                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

                                }
                            }
                        }
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
            if (data != null) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                image.setImageBitmap(imageBitmap)
            }else{
                val i = Intent(this, EditCheck::class.java)
                startActivity(i)
                super.onActivityResult(requestCode, resultCode, data)
            }

        }
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




    override fun onBackPressed() {
        val i = Intent()
        var img: ImageView = findViewById<ImageView>(R.id.imageView)
        var ed1 = findViewById<EditText>(R.id.editTextTextPersonName8)
        var ed2: EditText = findViewById<EditText>(R.id.editTextTextPersonName9)
        var ed3: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        var ed4: EditText = findViewById<EditText>(R.id.editTextTextPersonName10)
        var ed5: EditText = findViewById<EditText>(R.id.editTextTextPersonName11)
        var ed6: EditText = findViewById<EditText>(R.id.editTextTextPersonName16)
        var ed7: EditText = findViewById<EditText>(R.id.editTextTextMultiLine)
        var ed8: EditText = findViewById<EditText>(R.id.editTextTextMultiLine2)

        i.putExtra("image",img.drawToBitmap())
        i.putExtra("Name", ed1.text.toString())
        i.putExtra("Nickname",ed2.text.toString())
        i.putExtra("email",ed3.text.toString())
        i.putExtra("location",ed4.text.toString())
        i.putExtra("skill1",ed5.text.toString())
        i.putExtra("skill2",ed6.text.toString())
        i.putExtra("description1",ed7.text.toString())
        i.putExtra("description2",ed8.text.toString())
        setResult(Activity.RESULT_OK,i)

        super.onBackPressed()

    }




}