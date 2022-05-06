package it.polito.showprofileactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.polito.listapplication.Advertisement
import it.polito.listapplication.AdvertisementAdapter


class TimeSlotListFragment : Fragment(R.layout.fragment_time_slot_list) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_time_slot_list, container, false)

//        setContentView(R.layout.fragment_advlist)
        val rv = root.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(root.context)
        val adapter = AdvertisementAdapter(createAdvs(20))
        rv.adapter = adapter

        return root
    }

    private fun createAdvs(n: Int): MutableList<Advertisement> {
        val l = mutableListOf<Advertisement>()
        for (i in 1..n) {
            val i = Advertisement(i,"title$i", "description$i", "data&time$i","duration$i","location$i")
            l.add(i)
        }
        return l
    }

}