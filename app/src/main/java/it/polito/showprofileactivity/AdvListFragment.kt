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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.polito.listapplication.Advertisement
import it.polito.listapplication.AdvertisementAdapter
import org.json.JSONException
import org.json.JSONObject


class AdvListFragment : Fragment(R.layout.fragment_advlist) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_advlist, container, false)

        val rv = root.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = AdvertisementAdapter(createAdvs(2000))
        rv.adapter = adapter
        return root
    }

    private fun createAdvs(n: Int): MutableList<Advertisement> {
        val l = mutableListOf<Advertisement>()
        for (i in 1..n) {
            val i = Advertisement(i,"name$i", "role$i")
            l.add(i)
        }
        return l
    }

}