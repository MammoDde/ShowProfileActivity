package it.polito.showprofileactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.*
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
                    R.id.menu_open_website -> {
                        Toast.makeText(this, "Camera!", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.menu_show_toast -> {
                        Toast.makeText(this, "Gallery!", Toast.LENGTH_LONG).show()
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