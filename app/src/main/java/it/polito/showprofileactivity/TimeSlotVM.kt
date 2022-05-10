package it.polito.showprofileactivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlin.concurrent.thread

class TimeSlotVM(application: Application): AndroidViewModel(application) {

    val repo = TimeSlotRepo(application)

    val value: LiveData<List<TimeSlot>> = repo.timeSlots()

    val size: LiveData<Int> = repo.count()

    fun add(timeSlot: TimeSlot) {
        thread {
            repo.add(timeSlot)
        }
    }

    fun update(timeSlot: TimeSlot) {
        thread {
            repo.update(timeSlot)
        }
    }


}
