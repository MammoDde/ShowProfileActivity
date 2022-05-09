package it.polito.showprofileactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlin.concurrent.thread

class TimeSlotVM(application: Application): AndroidViewModel(application) {

    val repo = TimeSlotRepo(application)

    val value: LiveData<List<TimeSlot>> = repo.timeSlots()

    fun add(timeSlot: TimeSlot) {
        thread {
            repo.add(timeSlot)
        }
    }


}
