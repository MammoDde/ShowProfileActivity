package it.polito.showprofileactivity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu


class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var editButton: ImageButton = findViewById(R.id.imageButton2)


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