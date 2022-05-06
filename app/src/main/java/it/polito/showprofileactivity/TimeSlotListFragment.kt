package it.polito.showprofileactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        val rv = root.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(root.context)


        val l = mutableListOf<Advertisement>()

        //defining VievModel
        val vm by viewModels<AdvertisementsVM>()
        vm.items.observe(this){
            //qui i dati da caricare
            println(it)
            for (i in 1..it.size) {
                val i = Advertisement(it[i].id, it[i].title, it[i].description, it[i].dateAndTime, it[i].duration, it[i].location)
                l.add(i)
            }
        }

        //qua andiamo a caricare i dati nell'interfaccia
        val adapter = AdvertisementAdapter(l)
        rv.adapter = adapter


        return root
    }


}