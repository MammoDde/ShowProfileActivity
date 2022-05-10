package it.polito.showprofileactivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

class TimeSlotRepo(application: Application) {
    private val TimeSlotDAO = TimeSlotDB.getDatabase(application).TimeSlotDAO()

    fun count(): LiveData<Int> = TimeSlotDAO.count()

    fun add(timeSlot: TimeSlot) {
        val i = TimeSlot().also {
            it.title = timeSlot.title
            it.description = timeSlot.description
            it.dateAndTime = timeSlot.dateAndTime
            it.duration = timeSlot.duration
            it.location = timeSlot.location
        }
        TimeSlotDAO.insertTimeSlot(i)
    }

    fun update(timeSlot: TimeSlot) {
        val i = TimeSlot().also {
            it.title = timeSlot.title
            it.description = timeSlot.description
            it.dateAndTime = timeSlot.dateAndTime
            it.duration = timeSlot.duration
            it.location = timeSlot.location
        }
        TimeSlotDAO.updateTimeSlot(i)
    }

    fun timeSlots(): LiveData<List<TimeSlot>> = TimeSlotDAO.findAll()
}