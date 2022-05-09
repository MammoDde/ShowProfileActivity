package it.polito.showprofileactivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
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
        var title = view.findViewById<TextView>(R.id.slot_title)
        //costrutto necessario per leggere dai dati passati dal bundle
        arguments?.let {
            if(it.isEmpty){
                Log.d("back","bundle is empty")
                requireActivity()
                    .onBackPressedDispatcher
                    .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                        @SuppressLint("CutPasteId")
                        override fun handleOnBackPressed() {
                            Log.d("back", "Fragment back pressed invoked from fab")

                            view.findViewById<EditText>(R.id.slot_title_edit).text = view.findViewById<EditText>(R.id.slot_title_edit).text
                            view.findViewById<EditText>(R.id.slot_description_edit).text = view.findViewById<EditText>(R.id.slot_description_edit).text
                            view.findViewById<EditText>(R.id.slot_date_and_time_edit).text = view.findViewById<EditText>(R.id.slot_date_and_time_edit).text
                            view.findViewById<EditText>(R.id.slot_duration_edit).text = view.findViewById<EditText>(R.id.slot_duration_edit).text
                            view.findViewById<EditText>(R.id.slot_location_edit).text = view.findViewById<EditText>(R.id.slot_location_edit).text

                            val adv = TimeSlot()
                            adv.id = 10
                            adv.title= view.findViewById<EditText>(R.id.slot_title_edit).text.toString()
                            adv.description= view.findViewById<EditText>(R.id.slot_description_edit).text.toString()
                            adv.dateAndTime= view.findViewById<EditText>(R.id.slot_date_and_time_edit).text.toString()
                            adv.duration= view.findViewById<EditText>(R.id.slot_duration_edit).text.toString()
                            adv.location= view.findViewById<EditText>(R.id.slot_location_edit).text.toString()
                            //TODO : insert into db
                            Log.d("new adv", adv.toString())

                            //TODO : nuovo adv passato correttamente, sarebbe da inserire nel db

                            // if you want onBackPressed() to be called as normal afterwards
                            if (isEnabled) {
                                isEnabled = false
                                requireActivity().onBackPressed()
                            }
                        }
                    }
                    )
            }else {
                Log.d("back","bundle is not empty")
                view.findViewById<EditText>(R.id.slot_title_edit).setText(it.getString("title"))
                view.findViewById<EditText>(R.id.slot_description_edit)
                    .setText(it.getString("description"))
                view.findViewById<EditText>(R.id.slot_date_and_time_edit)
                    .setText(it.getString("dateAndTime"))
                view.findViewById<EditText>(R.id.slot_duration_edit)
                    .setText(it.getString("duration"))
                view.findViewById<EditText>(R.id.slot_location_edit)
                    .setText(it.getString("location"))

                requireActivity()
                    .onBackPressedDispatcher
                    .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            Log.d("back", "Fragment back pressed invoked from edit")
                            // Do custom work here
                            view.findViewById<EditText>(R.id.slot_title_edit).text = view.findViewById<EditText>(R.id.slot_title_edit).text
                            // if you want onBackPressed() to be called as normal afterwards
                            Log.d("edit_title",
                                view.findViewById<EditText>(R.id.slot_title_edit).text.toString()
                            )
                            //il testo cambia ed è corretto ma poi nella lista a schermo
                            //ovviamente no perché quella va a prendere dal db
                            //se facciamo qui la update dovrebbe essere tutto a posto
                            if (isEnabled) {
                                isEnabled = false
                                requireActivity().onBackPressed()
                            }
                        }
                    }
                    )

            }
        }

    }


}
