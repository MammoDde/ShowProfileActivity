package it.polito.showprofileactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TimeSlotVM(application: Application): AndroidViewModel(application) {

    val repo = TimeSlotRepo(application)

    val value: LiveData<Int> = repo.count()

}
