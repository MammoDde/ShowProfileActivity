package it.polito.showprofileactivity

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class TimeSlotEditFragment : Fragment() {

    val vm by viewModels<TimeSlotVM>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // android:configChanges="orientation|screenSize" da aggiungere sotto activity nel manifest
        // serve per avere la stessa toolbar quando ruotiamo lo schermo
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setHomeButtonEnabled(false)

        return inflater.inflate(R.layout.fragment_time_slot_edit, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //costrutto necessario per leggere dai dati passati dal bundle
        arguments?.let {
            if(it.isEmpty){
                //CASO ADD: in questo if andiamo a creare un nuovo time slot
                //title of action bar changed
                (activity as MainActivity).supportActionBar?.setTitle(R.string.create_new_time_slot)
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

                            adv.title = view.findViewById<EditText>(R.id.slot_title_edit).text.toString()
                            adv.description = view.findViewById<EditText>(R.id.slot_description_edit).text.toString()
                            adv.dateAndTime = view.findViewById<EditText>(R.id.slot_date_and_time_edit).text.toString()
                            adv.duration = view.findViewById<EditText>(R.id.slot_duration_edit).text.toString()
                            adv.location = view.findViewById<EditText>(R.id.slot_location_edit).text.toString()

                            //insert into db
                            Log.d("new adv", adv.toString())
                            vm.add(adv)

                            // if you want onBackPressed() to be called as normal afterwards
                            if (isEnabled) {
                                isEnabled = false
                                requireActivity().onBackPressed()
                            }
                        }
                    }
                    )
            }else {
                //CASO EDIT: modifica di un fragment esistente
                Log.d("back","bundle is not empty")
                //title of action bar changed
                (activity as MainActivity).supportActionBar?.setTitle(R.string.edit_time_slot)
                //settaggio campi nella edit
                view.findViewById<EditText>(R.id.slot_title_edit).setText(it.getString("title"))
                view.findViewById<EditText>(R.id.slot_description_edit).setText(it.getString("description"))
                view.findViewById<EditText>(R.id.slot_date_and_time_edit).setText(it.getString("dateAndTime"))
                view.findViewById<EditText>(R.id.slot_duration_edit).setText(it.getString("duration"))
                view.findViewById<EditText>(R.id.slot_location_edit).setText(it.getString("location"))

                requireActivity()
                    .onBackPressedDispatcher
                    .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            Log.d("back", "Fragment back pressed invoked from edit")
                            // Do custom work here
                            val updatedTimeSlot = TimeSlot()
                            updatedTimeSlot.title = view.findViewById<EditText>(R.id.slot_title_edit).text.toString()
                            updatedTimeSlot.description = view.findViewById<EditText>(R.id.slot_description_edit).text.toString()
                            updatedTimeSlot.dateAndTime = view.findViewById<EditText>(R.id.slot_date_and_time_edit).text.toString()
                            updatedTimeSlot.duration = view.findViewById<EditText>(R.id.slot_duration_edit).text.toString()
                            updatedTimeSlot.location = view.findViewById<EditText>(R.id.slot_location_edit).text.toString()

                            // if you want onBackPressed() to be called as normal afterwards
                            Log.d("back", updatedTimeSlot.toString())

                            //il testo cambia ed è corretto ma poi nella lista a schermo
                            //ovviamente no perché quella va a prendere dal db
                            //se facciamo qui la update dovrebbe essere tutto a posto

                            //proviamo l'update
                            vm.update(updatedTimeSlot)


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
