package it.polito.showprofileactivity

import android.app.Application
import androidx.lifecycle.LiveData

class TimeSlotRepo(application: Application) {
    private val TimeSlotDAO = TimeSlotDB.getDatabase(application).TimeSlotDAO()
    fun count(): LiveData<Int> = TimeSlotDAO.count()
}