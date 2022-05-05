package it.polito.showprofileactivity

import android.app.FragmentManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.json.JSONException
import org.json.JSONObject


private const val REQUEST_IMAGE_CAPTURE = 1
private const val REQUEST_ACTION_PICK = 2

private const val name = "icon"
private var currentPhotoPath: String? = null

class EditProfileFragment : Fragment(R.layout.fragment_edit) {

    private var photo: Photo = Photo()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPrefR = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val profileInfo =
            "{'full name' : '${getString(R.string.full_name)}', nickname : '${getString(R.string.nickname)}', " +
                    "email : '${getString(R.string.email)}', location : '${getString(R.string.location)}', skill1 : '${
                        getString(
                            R.string.skill1
                        )
                    }'," +
                    " skill2 : '${getString(R.string.skill2)}', description1 : '${getString(R.string.description1)}', " +
                    "description2 : '${getString(R.string.description2)}', " + "path: '${currentPhotoPath.toString()}'}"

        val json = sharedPrefR?.getString("profile", profileInfo)?.let { JSONObject(it) }
        val root = inflater.inflate(R.layout.fragment_edit, container, false)
        val img: ImageView = root.findViewById(R.id.imageView)

        val tv1: TextView = root.findViewById(R.id.edit_fullname)
        val tv2: TextView = root.findViewById(R.id.edit_nickname)
        val tv3: TextView = root.findViewById(R.id.edit_email)
        val tv4: TextView = root.findViewById(R.id.edit_location)
        val tv5: TextView = root.findViewById(R.id.edit_skill1)
        val tv6: TextView = root.findViewById(R.id.edit_skill2)
        val tv7: TextView = root.findViewById(R.id.edit_description1)
        val tv8: TextView = root.findViewById(R.id.edit_description2)

        if (json != null) {
            tv1.text = (json.get("full name").toString())
            tv2.text = (json.get("nickname").toString())
            tv3.text = (json.get("email").toString())
            tv4.text = (json.get("location").toString())
            tv5.text = (json.get("skill1").toString())
            tv6.text = (json.get("skill2").toString())
            tv7.text = (json.get("description1").toString())
            tv8.text = (json.get("description2").toString())

            try {
                if (json.get("path").toString() == "null")
                    currentPhotoPath = null
                else
                    currentPhotoPath = json.get("path").toString()
            } catch (e: JSONException) {
                println(e)
            }
        }
        if (currentPhotoPath != null) {
            val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        }

        val saveButton = root.findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener{
            val tv = view?.findViewById<TextView>(R.id.edit_fullname)

            val profileInfo = "{'full name' : '${tv?.text}', nickname : '${tv?.text}', " +
                    "email : '${tv?.text}', location : '${tv?.text}', skill1 : '${tv?.text}'," +
                    " skill2 : '${tv?.text}', description1 : '${tv?.text}', " +
                    "description2 : '${tv?.text}', " + "path: '${currentPhotoPath}'}"
            val json = JSONObject(profileInfo)

            val sharedPref = activity?.getPreferences(MODE_PRIVATE)
            if (sharedPref != null) {
                with (sharedPref.edit()) {
                    putString("profile", json.toString())
                    apply()
                }
            }
        }

        return root
    }


}