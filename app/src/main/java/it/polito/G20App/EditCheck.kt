package it.polito.G20App

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class EditCheck : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_check, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //costrutto necessario per leggere dai dati passati dal bundle
        arguments?.let {
            if (it.getSerializable("id") != null) {
                val id = it.getString("id")
                view.findViewById<TextView>(R.id.textView6).text = id
            }
        }

    }


}
