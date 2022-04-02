package it.polito.showprofileactivity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu


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
                        val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivity(photoIntent);
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




}