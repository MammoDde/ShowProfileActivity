package it.polito.showprofileactivity

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [TimeSlotDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeSlotDetailsFragment : Fragment(R.layout.fragment_time_slot_details) {
    private var id1 : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(time_slot_menu: Menu, inflater: MenuInflater) {
        //menu item that allows editing the advertisement

        val inflater = MenuInflater(context)
        inflater.inflate(R.menu.time_slot_menu, time_slot_menu)

        super.onCreateOptionsMenu(time_slot_menu, inflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_time_slot_details, container, false)
        //questa view dovrà avere un menu che permette di modificarne i campi

        //recupero dati dal Bundle
        arguments.let {
            if (it != null) {
                println(it.getString("dateAndTime"))
                //abbiamo qui l'id ma noi dobbiamo passarlo alla funzione onOptionsItemSelected
                id1 = it.getString("id")!!.toInt()
                root.findViewById<TextView>(R.id.slot_title).text = it.getString("title")
                root.findViewById<TextView>(R.id.slot_description).text = it.getString("description")
                root.findViewById<TextView>(R.id.slot_date_and_time).text = it.getString("dateAndTime")
                root.findViewById<TextView>(R.id.slot_duration).text = it.getString("duration")
                root.findViewById<TextView>(R.id.slot_location).text = it.getString("location")
            }

        }

        return root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        // Handle item selection
        return when (item.itemId) {
            R.id.icona -> {
                //aprire TimeSlotEditFragment.kt
                val title = view?.findViewById<TextView>(R.id.slot_title)?.text.toString()
                val description = view?.findViewById<TextView>(R.id.slot_description)?.text.toString()
                val dateAndTime = view?.findViewById<TextView>(R.id.slot_date_and_time)?.text.toString()
                val duration = view?.findViewById<TextView>(R.id.slot_duration)?.text.toString()
                val location = view?.findViewById<TextView>(R.id.slot_location)?.text.toString()

                var b = Bundle()
                //TODO: problema quando siamo nell'edit da qui non viene passato l'id quindi l'app crasha
                //bundle.putString("id", )
                b.putString("id", id1.toString())
                b.putString("title", title)
                b.putString("description", description)
                b.putString("dateAndTime", dateAndTime)
                b.putString("duration", duration)
                b.putString("location", location)
                findNavController().navigate(R.id.action_nav_slot_details_to_timeSlotEditFragment, b)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}