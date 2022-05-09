package it.polito.showprofileactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class TimeSlotEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_time_slot_edit, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //costrutto necessario per leggere dai dati passati dal bundle
        arguments?.let {
            view.findViewById<EditText>(R.id.slot_title_edit).setText(it.getString("title"))
            view.findViewById<EditText>(R.id.slot_description_edit).setText(it.getString("description"))
            view.findViewById<EditText>(R.id.slot_date_and_time_edit).setText(it.getString("dateAndTime"))
            view.findViewById<EditText>(R.id.slot_duration_edit).setText(it.getString("duration"))
            view.findViewById<EditText>(R.id.slot_location_edit).setText(it.getString("location"))

        }

    }


}
