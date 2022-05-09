package it.polito.showprofileactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TimeSlotListFragment : Fragment(R.layout.fragment_time_slot_list) {

    val vm by viewModels<TimeSlotVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_time_slot_list, container, false)
        val rv = root.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(root.context)

        val l = mutableListOf<TimeSlot>()
        //creo elemento prova da passare alla mutableList
        val prova = TimeSlot()
        val prova1 = TimeSlot()

        prova.id = 1
        prova.title = "titolo"
        prova.description = "descrizione"
        prova.dateAndTime = "Data personale"
        prova.location = "Torino"
        prova.duration = "2h"

        prova1.id = 2
        prova1.title = "titolo1"
        prova1.description = "descrizione1"
        prova1.dateAndTime = "Data personale1"
        prova1.location = "Torino1"
        prova1.duration = "2h1"


        l.add(prova)
        l.add(prova1)
        //defining ViewModel
        vm.value.observe(viewLifecycleOwner) {
            //qui i dati da caricare
            println(it)
        }

        //qua andiamo a caricare i dati nell'interfaccia
        val adapter = TimeSlotAdapter(l)
        rv.adapter = adapter

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

       // val vm = activity?.let { ViewModelProvider(it).get(AdvertisementsVM::class.java) }

/*
        val rv = view.findViewById<RecyclerView>(R.id.rv)
        if (rv != null) {
            rv.layoutManager = LinearLayoutManager(requireView().context)
        }
        val l = mutableListOf<Advertisement>()

        //defining ViewModel
        vm.items.observe(viewLifecycleOwner) {
            //qui i dati da caricare
            println("Inside viewmodel")

            for(i in 1..it.size) {
                val i = Advertisement(it[i].id, it[i].title, it[i].description, it[i].dateAndTime, it[i].duration, it[i].location)
                l.add(i)
            }

        }
        //qua andiamo a caricare i dati nell'interfaccia
        val adapter = AdvertisementAdapter(l)
        if (rv != null) {
            rv.adapter = adapter
        }*/
    }



    override fun onStart() {
        super.onStart()



    }
}