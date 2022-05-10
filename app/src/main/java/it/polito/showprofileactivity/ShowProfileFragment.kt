package it.polito.showprofileactivity

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONException
import org.json.JSONObject

private var currentPhotoPath: String? = null

class ShowProfileFragment : Fragment(R.layout.fragment_home) {
    private var photo: Photo = Photo()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //Tutte le volte che si ritorna nell'HomeFragment viene settato come clicked il tasto home nel menu laterale

        //Caricamento shared preferences
        val sharedPrefR = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val profileInfo = "{'full name' : '${getString(R.string.full_name)}', nickname : '${getString(R.string.nickname)}', " +
                "email : '${getString(R.string.email)}', location : '${getString(R.string.location)}', skill1 : '${getString(R.string.skill1)}'," +
                " skill2 : '${getString(R.string.skill2)}', description1 : '${getString(R.string.description1)}', " +
                "description2 : '${getString(R.string.description2)}', " + "path: '${currentPhotoPath.toString()}'}"

        val json = sharedPrefR?.getString("profile", profileInfo)?.let { JSONObject(it) }
        val img: ImageView = root.findViewById(R.id.imageView)

        val tv1: TextView = root.findViewById(R.id.fullname)
        val tv2: TextView = root.findViewById(R.id.nickname)
        val tv3: TextView = root.findViewById(R.id.email)
        val tv4: TextView = root.findViewById(R.id.location)
        val tv5: TextView = root.findViewById(R.id.skill1)
        val tv6: TextView = root.findViewById(R.id.skill2)
        val tv7: TextView = root.findViewById(R.id.description1)
        val tv8: TextView = root.findViewById(R.id.description2)

        if(json != null){
            tv1.text = (json.get("full name").toString())
            tv2.text = (json.get("nickname").toString())
            tv3.text = (json.get("email").toString())
            tv4.text = (json.get("location").toString())
            tv5.text = (json.get("skill1").toString())
            tv6.text = (json.get("skill2").toString())
            tv7.text = (json.get("description1").toString())
            tv8.text = (json.get("description2").toString())

            try {
                if(json.get("path").toString() == "null")
                    currentPhotoPath = null
                else
                    currentPhotoPath = json.get("path").toString()
            } catch (e : JSONException) {
                println(e)
            }
        }
        if(currentPhotoPath != null){
            val bitmap: Bitmap? = photo.loadImageFromStorage(currentPhotoPath, "icon")
            img.setImageBitmap(bitmap)
        }
        return root

    }

}